package org.ginwithouta.shortlink.project.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDeviceStatisticsDO;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkLocaleStatisticsDO;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatsDO;
import org.ginwithouta.shortlink.project.dao.mapper.*;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.*;
import org.ginwithouta.shortlink.project.service.ShortLinkStatisticsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ginwithouta
 * Generate at 2023/12/2
 * 短链接监控接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkStatisticsServiceImpl extends ServiceImpl<ShortLinkStatsMapper, ShortLinkStatsDO> implements ShortLinkStatisticsService {

    private final ShortLinkStatsLocaleMapper statsLocaleMapper;
    private final ShortLinkAccessLogsMapper accessLogsMapper;
    private final ShortLinkStatsBrowserMapper statsBrowserMapper;
    private final ShortLinkOsStatsMapper statsOsMapper;
    private final ShortLinkDeviceStatsMapper statsDeviceMapper;

    @Override
    public ShortLinkStatsRespDTO oneShortLinkStatistics(ShortLinkStatsReqDTO requestParam) {
        List<ShortLinkStatsDO> statsListByShortLink = baseMapper.listStatisticsByShortLink(requestParam);
        if (CollUtil.isEmpty(statsListByShortLink)) {
            // 如果当前短链接没有记录，直接返回 NULL 就行，不用管
            return null;
        }
        /*
         * 短链接监控之基础数据（PV，UV，UIP）
         */
        List<ShortLinkStatsDailyRespDTO> dailyStatistics = new ArrayList<>();
        // 通过 requestParam 中传入的 startDate 以及 endDate 生成中间每一天的日期，例如 [2023/11/1, 2023/11/2, 2023/11/3, ...]
        List<String> eachDates = DateUtil.rangeToList(DateUtil.parse(requestParam.getStartDate()), DateUtil.parse(requestParam.getEndDate()), DateField.DAY_OF_MONTH)
                .stream()
                .map(DateUtil::formatDate)
                .toList();
        // 遍历每一天，看短链接监控是否有当天的监控信息，如果有则登记，如果没有，则赋值一个 0
        eachDates.forEach(eachDay -> statsListByShortLink.stream()
            .filter(stats -> Objects.equals(eachDay, DateUtil.formatDate(stats.getDate())))
            .findFirst()
            .ifPresentOrElse(stats -> {
                ShortLinkStatsDailyRespDTO dailyRespDTO = ShortLinkStatsDailyRespDTO.builder()
                        .date(eachDay)
                        .pv(stats.getPv())
                        .uip(stats.getUip())
                        .uv(stats.getUv())
                        .build();
                dailyStatistics.add(dailyRespDTO);
            }, () -> {
                ShortLinkStatsDailyRespDTO dailyRespDTO = ShortLinkStatsDailyRespDTO.builder()
                        .date(eachDay)
                        .pv(0)
                        .uip(0)
                        .uv(0)
                        .build();
                dailyStatistics.add(dailyRespDTO);
            }));
        /*
         * 短链接监控之地区（仅国内）
         */
        List<ShortLinkStatsLocaleCNRespDTO> localeCNRespDTOS = new ArrayList<>();
        List<ShortLinkLocaleStatisticsDO> listLocaleByShortLink = statsLocaleMapper.listLocaleByShortLink(requestParam);
        int localeCNSum = listLocaleByShortLink.stream()
                .mapToInt(ShortLinkLocaleStatisticsDO::getCnt)
                .sum();
        listLocaleByShortLink.forEach(each -> {
            double ratio = (double) each.getCnt() / localeCNSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsLocaleCNRespDTO localeCNRespDTO = ShortLinkStatsLocaleCNRespDTO.builder()
                    .cnt(each.getCnt())
                    .locale(each.getProvince())
                    .ratio(actualRatio)
                    .build();
            localeCNRespDTOS.add(localeCNRespDTO);
        });
        /*
         * 短链接监控之 24 小时访问分布情况
         */
        List<Integer> hoursStats = new ArrayList<>(24);
        List<ShortLinkStatsDO> listHoursByShortLink = baseMapper.listHoursStatisticsByShortLink(requestParam);
        for (int i = 0; i < 24; ++i) {
            AtomicInteger hour = new AtomicInteger(i);
            int hourCnt = listHoursByShortLink.stream()
                    .filter(each -> Objects.equals(each.getHour(), hour.get()))
                    .findFirst()
                    .map(ShortLinkStatsDO::getPv)
                    .orElse(0);
            hoursStats.add(hourCnt);
        }
        /*
         * 短链接监控之高频访问 IP
         */
        List<ShortLinkStatsTopIpRespDTO> topIpStats = new ArrayList<>();
        List<HashMap<String, Object>> listTopIpByShortLink = accessLogsMapper.listTopIpByShortLink(requestParam);
        listTopIpByShortLink.forEach(each -> {
            ShortLinkStatsTopIpRespDTO statsTopIpRespDTO = ShortLinkStatsTopIpRespDTO.builder()
                    .ip(each.get("ip").toString())
                    .cnt(Integer.parseInt(each.get("count").toString()))
                    .build();
            topIpStats.add(statsTopIpRespDTO);
        });
        /*
         * 短链接监控之一周内的访问情况
         */
        List<Integer> weekdayStats = new ArrayList<>();
        List<ShortLinkStatsDO> listWeekStatsByShortLink = baseMapper.listWeekStatsByShortLink(requestParam);
        for (int i = 1; i < 8; ++i) {
            AtomicInteger weekday = new AtomicInteger(i);
            int weekdayCnt = listWeekStatsByShortLink.stream()
                    .filter(each -> Objects.equals(each.getWeekday(), weekday.get()))
                    .findFirst()
                    .map(ShortLinkStatsDO::getPv)
                    .orElse(0);
            weekdayStats.add(weekdayCnt);
        }
        /*
         * 短链接监控之浏览器
         */
        List<ShortLinkStatsBrowserRespDTO> browserStats = new ArrayList<>();
        List<HashMap<String, Object>> listBrowserStatsByShortLink = statsBrowserMapper.listBrowserStatsByShortLink(requestParam);
        int browserSum = listBrowserStatsByShortLink.stream()
                .mapToInt(each -> Integer.parseInt(each.get("count").toString()))
                .sum();
        listBrowserStatsByShortLink.forEach(each -> {
            double ratio = (double) Integer.parseInt(each.get("count").toString()) / browserSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsBrowserRespDTO shortLinkStatsBrowserRespDTO = ShortLinkStatsBrowserRespDTO.builder()
                    .cnt(Integer.parseInt(each.get("count").toString()))
                    .browser(each.get("browser").toString())
                    .ratio(actualRatio)
                    .build();
            browserStats.add(shortLinkStatsBrowserRespDTO);
        });
        /*
         * 短链接监控之操作系统
         */
        List<ShortLinkStatsOsRespDTO> osStats = new ArrayList<>();
        List<HashMap<String, Object>> listOsStatsByShortLink = statsOsMapper.listOsStatsByShortLink(requestParam);
        int osSum = listOsStatsByShortLink.stream()
                .mapToInt(each -> Integer.parseInt(each.get("count").toString()))
                .sum();
        listOsStatsByShortLink.forEach(each -> {
            double ratio = (double) Integer.parseInt(each.get("count").toString()) / osSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsOsRespDTO osRespDTO = ShortLinkStatsOsRespDTO.builder()
                    .cnt(Integer.parseInt(each.get("count").toString()))
                    .os(each.get("os").toString())
                    .ratio(actualRatio)
                    .build();
            osStats.add(osRespDTO);
        });
        /*
         * 短链接监控之访客类型（指定时间内的访客：新房客，老访客）
         */
        List<ShortLinkStatsUvRespDTO> uvTypeStats = new ArrayList<>();
        HashMap<String, Object> findUvTypeByShortLink = accessLogsMapper.findUvTypeCntByShortLink(requestParam);
        int oldUserCnt = Integer.parseInt(
                Optional.ofNullable(findUvTypeByShortLink)
                        .map(each -> each.get("oldUserCnt"))
                        .map(Object::toString)
                        .orElse("0")
        );
        int newUserCnt = Integer.parseInt(
                Optional.ofNullable(findUvTypeByShortLink)
                        .map(each -> each.get("newUserCnt"))
                        .map(Object::toString)
                        .orElse("0")
        );
        int uvSum = oldUserCnt + newUserCnt;
        double oldRatio = (double) oldUserCnt / uvSum;
        double actualOldRatio = Math.round(oldRatio * 100.0) / 100.0;
        double newRatio = (double) newUserCnt / uvSum;
        double actualNewRatio = Math.round(newRatio * 100.0) / 100.0;
        ShortLinkStatsUvRespDTO newUvRespDTO = ShortLinkStatsUvRespDTO.builder()
                .uvType("newUser")
                .cnt(newUserCnt)
                .ratio(actualNewRatio)
                .build();
        uvTypeStats.add(newUvRespDTO);
        ShortLinkStatsUvRespDTO oldUvRespDTO = ShortLinkStatsUvRespDTO.builder()
                .uvType("oldUser")
                .cnt(oldUserCnt)
                .ratio(actualOldRatio)
                .build();
        uvTypeStats.add(oldUvRespDTO);
        /*
         * 短链接监控之访问设备
         */
        List<ShortLinkStatsDeviceRespDTO> deviceStats = new ArrayList<>();
        List<ShortLinkDeviceStatisticsDO> listDeviceStatsByShortLink = statsDeviceMapper.listDeviceStatsByShortLink(requestParam);
        int deviceSum = listDeviceStatsByShortLink.stream()
                .mapToInt(ShortLinkDeviceStatisticsDO::getCnt)
                .sum();
        listDeviceStatsByShortLink.forEach(each -> {
            double ratio = (double) each.getCnt() / deviceSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsDeviceRespDTO deviceRespDTO = ShortLinkStatsDeviceRespDTO.builder()
                    .cnt(each.getCnt())
                    .device(each.getDevice())
                    .ratio(actualRatio)
                    .build();
            deviceStats.add(deviceRespDTO);
        });
    }
}
