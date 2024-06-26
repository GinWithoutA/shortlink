package org.ginwithouta.shortlink.project.mq.consumer;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.project.common.convention.exception.ServiceException;
import org.ginwithouta.shortlink.project.dao.entity.*;
import org.ginwithouta.shortlink.project.dao.mapper.*;
import org.ginwithouta.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import org.ginwithouta.shortlink.project.dto.biz.StatsIncrementMapperDTO;
import org.ginwithouta.shortlink.project.mq.idempotent.MessageQueueIdempotentHandler;
import org.ginwithouta.shortlink.project.service.ShortLinkGoToService;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.REDIS_LOCK_GID_UPDATE_KEY;
import static org.ginwithouta.shortlink.project.common.constant.ShortLinkConstant.AMAP_REMOTE_URL;
import static org.ginwithouta.shortlink.project.common.enums.ShortLinkErrorCodeEnums.SHORT_LINK_STATS_MQ_NOT_ACCOMPLISH;

/**
 * @author Ginwithouta
 * Generate at 2024/2/14
 * 短链接监控状态保存消息队列消费者
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShortLinkStatsSaveConsumer implements StreamListener<String, MapRecord<String, String, String>> {

    private final RedissonClient redissonClient;
    private final ShortLinkGoToService shortLinkGoToService;
    private final ShortLinkStatsMapper shortLinkStatsMapper;
    private final ShortLinkLocaleStatsMapper shortLinkLocaleStatsMapper;
    private final ShortLinkOsStatsMapper shortLinkOsStatsMapper;
    private final ShortLinkBrowserStatsMapper shortLinkBrowserStatsMapper;
    private final ShortLinkDeviceStatsMapper shortLinkDeviceStatsMapper;
    private final ShortLinkNetworkStatsMapper shortLinkNetworkStatsMapper;
    private final ShortLinkAccessLogsMapper shortLinkAccessLogsMapper;
    private final ShortLinkMapper shortLinkMapper;
    private final ShortLinkTodayStatsMapper shortLinkTodayStatsMapper;
    private final MessageQueueIdempotentHandler messageQueueIdempotentHandler;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${short-link.stats.locale.amap-key}")
    private String statsLocaleAMapKey;

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        String stream = message.getStream();
        RecordId id = message.getId();
        if (!messageQueueIdempotentHandler.isMessageProcessed(id.toString())) {
            // 防止异常情况导致没有执行完成，需要添加完成标识，
            if (messageQueueIdempotentHandler.isAccomplish(id.toString())) {
                return;
            }
            throw new ServiceException(SHORT_LINK_STATS_MQ_NOT_ACCOMPLISH);
        }
        try {
            Map<String, String> producerMap = message.getValue();
            String fullShortUrl = producerMap.get("fullShortUrl");
            if (StrUtil.isNotBlank(fullShortUrl)) {
                String gid = producerMap.get("gid");
                ShortLinkStatsRecordDTO statsRecord = JSON.parseObject(producerMap.get("statsRecord"), ShortLinkStatsRecordDTO.class);
                actualSaveShortLinkStats(fullShortUrl, gid, statsRecord);
            }
            stringRedisTemplate.opsForStream().delete(Objects.requireNonNull(stream), id.getValue());
        } catch (Throwable ex) {
            // 某某某情况宕机了，最多就是10分钟不能用
            messageQueueIdempotentHandler.delMessageProcessed(id.toString());
            log.error("记录短链接监控消费异常", ex);
            throw ex;
        }
        messageQueueIdempotentHandler.setAccomplish(id.toString());
    }

    public void actualSaveShortLinkStats(String fullShortUrl, String gid, ShortLinkStatsRecordDTO statsRecord) {
        fullShortUrl = Optional.ofNullable(fullShortUrl).orElse(statsRecord.getFullShortUrl());
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(String.format(REDIS_LOCK_GID_UPDATE_KEY, fullShortUrl));
        RLock rLock = readWriteLock.readLock();
        rLock.lock();
        /*
         * 这里不再使用延迟队列
         * 原先我们使用延迟队列，是用来保证在高并发情况下，当当前锁已经被获取了之后，其他线程阻塞导致 Tomcat 线程池不断有新线程阻塞，最终OOM
         * 当我们添加了消息队列了之后，所有的这些请求都会在消息队列中，处理都交由消息队列匀速处理，因此不会出现 OOM 的情况
         * 此时，延迟队列的效果就没有这么大了
         */
        try {
            if (StrUtil.isBlank(gid)) {
                LambdaQueryWrapper<ShortLinkGoToDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkGoToDO.class)
                        .eq(ShortLinkGoToDO::getFullShortUrl, fullShortUrl);
                ShortLinkGoToDO shortLinkGoToDO = shortLinkGoToService.getOne(queryWrapper);
                gid = shortLinkGoToDO.getGid();
            }
            int week = DateUtil.dayOfWeekEnum(new Date()).getIso8601Value();
            int hour = DateUtil.hour(new Date(), true);
            ShortLinkStatsDO statsDO = ShortLinkStatsDO.builder()
                    .pv(1)
                    .uv(statsRecord.getUvFirstFlag() ? 1 : 0)
                    .uip(statsRecord.getUipFirstFlag() ? 1 : 0)
                    .hour(hour)
                    .weekday(week)
                    .fullShortUrl(fullShortUrl)
                    .date(new Date())
                    .build();
            shortLinkStatsMapper.shortLinkStats(statsDO);
            /*
             * 短链接监控之地区（通过高德地图 API 获取当前访问所属地区）
             */
            Map<String, Object> localeParamMap = new HashMap<>();
            localeParamMap.put("key", statsLocaleAMapKey);
            localeParamMap.put("ip", statsRecord.getRemoteAddr());
            String localeResultStr = HttpUtil.get(AMAP_REMOTE_URL, localeParamMap);
            JSONObject localeResultObj = JSON.parseObject(localeResultStr);
            String infoCode = localeResultObj.getString("infocode");
            String actualCity = null, actualProvince = null;
            ShortLinkLocaleStatsDO localeStatsDO;
            if (StrUtil.isNotBlank(infoCode) && StrUtil.equals(infoCode, "10000")) {
                String province = localeResultObj.getString("province");
                boolean unknownFlag = StrUtil.equals(province, "[]");
                localeStatsDO = ShortLinkLocaleStatsDO.builder()
                        .fullShortUrl(fullShortUrl)
                        .province(actualProvince = unknownFlag ? "未知" : province)
                        .city(actualCity = unknownFlag ? "未知" : localeResultObj.getString("city"))
                        .adcode(unknownFlag ? "未知" : localeResultObj.getString("adcode"))
                        .country("中国")
                        .date(new Date())
                        .cnt(1)
                        .build();
                shortLinkLocaleStatsMapper.shortLinkLocaleStats(localeStatsDO);
            }
            /*
             * 短链接监控之操作系统
             */
            ShortLinkOsStatsDO shortLinkOsStatisticsDO = ShortLinkOsStatsDO.builder()
                    .os(statsRecord.getOs())
                    .fullShortUrl(fullShortUrl)
                    .cnt(1)
                    .date(new Date())
                    .build();
            shortLinkOsStatsMapper.shortLinkOsStats(shortLinkOsStatisticsDO);
            /*
             * 短链接监控之浏览器
             */
            ShortLinkBrowserStatsDO shortLinkStatsBrowserDO = ShortLinkBrowserStatsDO.builder()
                    .browser(statsRecord.getBrowser())
                    .fullShortUrl(fullShortUrl)
                    .cnt(1)
                    .date(new Date())
                    .build();
            shortLinkBrowserStatsMapper.shortLinkStatsBrowser(shortLinkStatsBrowserDO);
            /*
             * 短链接监控之设备
             */
            ShortLinkDeviceStatsDO shortLinkDeviceStatisticsDO = ShortLinkDeviceStatsDO.builder()
                    .device(statsRecord.getDevice())
                    .fullShortUrl(fullShortUrl)
                    .cnt(1)
                    .date(new Date())
                    .build();
            shortLinkDeviceStatsMapper.shortLinkDeviceStats(shortLinkDeviceStatisticsDO);
            /*
             * 短链接监控之网络
             */
            ShortLinkNetworkStatsDO shortLinkNetworkStatisticsDO = ShortLinkNetworkStatsDO.builder()
                    .network(statsRecord.getNetwork())
                    .fullShortUrl(fullShortUrl)
                    .cnt(1)
                    .date(new Date())
                    .build();
            shortLinkNetworkStatsMapper.shortLinkNetworkStats(shortLinkNetworkStatisticsDO);
            /*
             * 短链接监控之访问日志
             *  （1）高频 IP
             *  （2）访客类型
             */
            ShortLinkAccessLogsDO shortLinkAccessLogsDO = ShortLinkAccessLogsDO.builder()
                    .user(statsRecord.getUv())
                    .ip(statsRecord.getRemoteAddr())
                    .browser(statsRecord.getBrowser())
                    .os(statsRecord.getOs())
                    .fullShortUrl(fullShortUrl)
                    .network(statsRecord.getNetwork())
                    .locale(StrUtil.join("-", "中国", actualProvince, actualCity))
                    .device(statsRecord.getDevice())
                    .build();
            shortLinkAccessLogsMapper.insert(shortLinkAccessLogsDO);
            /*
             * 短链接数据自增（PV UV UIP）
             */
            StatsIncrementMapperDTO incrementMapperDTO = StatsIncrementMapperDTO.builder()
                    .gid(gid)
                    .fullShortUrl(fullShortUrl)
                    .totalUv(statsRecord.getUvFirstFlag() ? 1 : 0)
                    .totalPv(1)
                    .totalUip(statsRecord.getUipFirstFlag() ? 1 : 0)
                    .build();
            shortLinkMapper.incrementStats(incrementMapperDTO);
            /*
             * 短链接监控之今日数据
             */
            ShortLinkTodayStatsDO statsTodayDO = ShortLinkTodayStatsDO.builder()
                    .todayUv(statsRecord.getUvFirstFlag() ? 1 : 0)
                    .todayPv(1)
                    .todayUip(statsRecord.getUipFirstFlag() ? 1 : 0)
                    .fullShortUrl(fullShortUrl)
                    .date(new Date())
                    .build();
            shortLinkTodayStatsMapper.shortLinkTodayStats(statsTodayDO);
        } catch (Throwable e) {
            log.error("短链接访问统计异常", e);
        } finally {
            rLock.unlock();
        }
    }
}
