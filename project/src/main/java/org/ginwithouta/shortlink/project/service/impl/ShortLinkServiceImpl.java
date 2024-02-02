package org.ginwithouta.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.project.common.convention.exception.ClientException;
import org.ginwithouta.shortlink.project.common.convention.exception.ServiceException;
import org.ginwithouta.shortlink.project.dao.entity.*;
import org.ginwithouta.shortlink.project.dao.mapper.*;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateBatchReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateBatchRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.project.service.ShortLinkGoToService;
import org.ginwithouta.shortlink.project.service.ShortLinkService;
import org.ginwithouta.shortlink.project.toolkit.HashUtil;
import org.ginwithouta.shortlink.project.toolkit.LinkUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.*;
import static org.ginwithouta.shortlink.project.common.constant.ShortLinkConstant.AMAP_REMOTE_URL;
import static org.ginwithouta.shortlink.project.common.constant.ShortLinkConstant.REDIRECT_TO_NOT_FOUND_URI;
import static org.ginwithouta.shortlink.project.common.enums.ShortLinkErrorCodeEnums.*;
import static org.ginwithouta.shortlink.project.common.enums.VaildDateTypeEnum.PERMANENT;
import static org.ginwithouta.shortlink.project.toolkit.LinkUtil.getLinkCacheValidDate;

/**
 * @Package : org.ginwithouta.shortlink.project.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 短链接接口层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    private final RedissonClient redissonClient;
    private final ShortLinkGoToService shortLinkGoToService;
    private final StringRedisTemplate stringRedisTemplate;
    private final ShortLinkAccessLogsMapper shortLinkAccessLogsMapper;
    private final ShortLinkStatsMapper shortLinkStatsMapper;
    private final ShortLinkOsStatisticsMapper shortLinkOsStatisticsMapper;
    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;
    private final ShortLinkDeviceStatisticsMapper shortLinkDeviceStatisticsMapper;
    private final ShortLinkStatsLocaleMapper shortLinkStatsLocaleMapper;
    private final ShortLinkNetworkStatisticsMapper shortLinkNetworkStatisticsMapper;
    private final ShortLinkStatsBrowserMapper shortLinkStatsBrowserMapper;

    @Value("${short-link.statistics.locale.amap-key}")
    private String statisticsLocaleAmapKey;

    private static final int MAX_GENERATE_TIMES = 100;

    @Override
    public ShortLinkCreateRespDTO createShorLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam.getOriginUrl(), requestParam.getDomain());
        String fullShortLinkUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setFullShortUrl(fullShortLinkUrl);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setDescription(requestParam.getDescribe());
        // TODO 恶意请求有风险，后续要改成异步的
        shortLinkDO.setFavicon(getFavicon(requestParam.getOriginUrl()));
        // 创建短链接的时候同时插入路由记录
        ShortLinkGoToDO shortLinkGoToDO = ShortLinkGoToDO.builder()
                .gid(requestParam.getGid())
                .fullShortUrl(fullShortLinkUrl)
                .build();
        try {
            int inserted = baseMapper.insert(shortLinkDO);
            boolean res = shortLinkGoToService.save(shortLinkGoToDO);
            if (inserted != 1 || !res) {
                throw new ServiceException(SHORT_LINK_CREATE_FAIL);
            }
        } catch (DuplicateKeyException ex) {
            // 多线程情况下，可能有多个线程获取到同一个短链接，并判空
            log.warn("短链接：并发异常，{} 重复入库", shortLinkDO.getFullShortUrl());
            throw new ServiceException(SHORT_LINK_BLOOM_FAIL);
        }
        // 创建的短链接直接放到 Redis 缓存中
        stringRedisTemplate.opsForValue().set(
                String.format(GOTO_SHORT_LINK_KEY, shortLinkDO.getFullShortUrl()),
                requestParam.getOriginUrl(),
                getLinkCacheValidDate(requestParam.getValidDate()), TimeUnit.MILLISECONDS);
        shortUriCreateCachePenetrationBloomFilter.add(shortLinkDO.getFullShortUrl());
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(shortLinkDO.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ShortLinkCreateBatchRespDTO createBatchShortLink(ShortLinkCreateBatchReqDTO requestParam) {
        if (requestParam.getOriginUrls().size() != requestParam.getDescribes().size()) {
            throw new ServiceException(SHORT_LINK_CREATE_FAIL);
        }
        List<String> fullShortUrls = new ArrayList<>();
        List<ShortLinkDO> shortLinkSaveBatch = new ArrayList<>();
        List<ShortLinkGoToDO> shortLinkGoToSaveBatch = new ArrayList<>();
        for (int i = 0; i < requestParam.getOriginUrls().size(); ++i) {
            String shortLinkSuffix = generateSuffix(requestParam.getOriginUrls().get(i), requestParam.getDomain());
            String fullShortLinkUrl = StrBuilder.create(requestParam.getDomain())
                    .append("/")
                    .append(shortLinkSuffix)
                    .toString();
            ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
            shortLinkDO.setOriginUrl(requestParam.getOriginUrls().get(i));
            shortLinkDO.setDescription(requestParam.getDescribes().get(i));
            shortLinkDO.setFullShortUrl(fullShortLinkUrl);
            shortLinkDO.setShortUri(shortLinkSuffix);
            // TODO 恶意请求有风险，后续要改成异步的
            shortLinkDO.setFavicon(getFavicon(requestParam.getOriginUrls().get(i)));
            shortLinkSaveBatch.add(shortLinkDO);
            fullShortUrls.add(fullShortLinkUrl);
            // 创建短链接的时候同时插入路由记录
            shortLinkGoToSaveBatch.add(ShortLinkGoToDO.builder()
                .gid(requestParam.getGid())
                .fullShortUrl(fullShortLinkUrl)
                .build());
        }
        boolean res = this.saveBatch(shortLinkSaveBatch);
        if (!res) {
            throw new ClientException(SHORT_LINK_CREATE_FAIL);
        }
        res = shortLinkGoToService.saveBatch(shortLinkGoToSaveBatch);
        if (!res) {
            throw new ClientException(SHORT_LINK_CREATE_FAIL);
        }
        // 创建的短链接直接放到 Redis 缓存中
        shortLinkSaveBatch.forEach(each -> {
            stringRedisTemplate.opsForValue().set(
                    String.format(GOTO_SHORT_LINK_KEY, each.getFullShortUrl()),
                    each.getOriginUrl(),
                    getLinkCacheValidDate(requestParam.getValidDate()), TimeUnit.MILLISECONDS);
            shortUriCreateCachePenetrationBloomFilter.add(each.getFullShortUrl());
        });
        return ShortLinkCreateBatchRespDTO.builder()
                .fullShortUrls(fullShortUrls)
                .originUrls(requestParam.getOriginUrls())
                .gid(requestParam.getGid())
                .build();
    }

    /**
     * 生成 BASE62 编码生成短链接后缀
     * @param domain 主域名
     * @param originUrl 原始链接
     * @return 短链接后缀
     */
    private String generateSuffix(String originUrl, String domain) {
        int customGenerateCount = 1;
        String shortUri;
        while (true) {
            originUrl = StrBuilder.create(originUrl)
                    .append(System.currentTimeMillis())
                    .toString();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCreateCachePenetrationBloomFilter.contains(StrBuilder.create(domain)
                    .append("/")
                    .append(shortUri)
                    .toString())) {
                return shortUri;
            }
            ++customGenerateCount;
            if (customGenerateCount > MAX_GENERATE_TIMES) {
                throw new ClientException(SHORT_LINK_CREATE_SUFFIX_OVER_FLOW);
            }
        }
    }

    /**
     * 获取原始链接网站图标
     */
    @SneakyThrows
    private String getFavicon(String url) {
        URL targetUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (HttpURLConnection.HTTP_OK == responseCode) {
            Document document = Jsoup.connect(url).get();
            Element faviconLink = document.select("link[rel~=(?i)^(shortcut )?icon]").first();
            if (faviconLink != null) {
                return faviconLink.attr("abs:href");
            }
        }
        return null;
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLinkList(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnable, 1)
                .orderByDesc(ShortLinkDO::getCreateTime);
        IPage<ShortLinkDO> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }

    @Override
    public List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParams) {
        QueryWrapper<ShortLinkDO> queryWrapper = Wrappers.query(new ShortLinkDO()).select("gid, count(*) as shortLinkCount")
                .in("gid", requestParams)
                .eq("enable", 1)
                .groupBy("gid");
        List<Map<String, Object>> resultMap = baseMapper.selectMaps(queryWrapper);
        return BeanUtil.copyToList(resultMap, ShortLinkGroupCountQueryRespDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateShortLink(ShortLinkUpdateReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                // TODO 这里直接查 FullShortLink 会造成读扩散问题
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getEnable, 1);
        ShortLinkDO selectShortLinkDO = baseMapper.selectOne(queryWrapper);
        if (selectShortLinkDO == null) {
            throw new ClientException(SHORT_LINK_NOT_EXIST);
        }
        if (Objects.equals(selectShortLinkDO.getGid(), requestParam.getGid())) {
            // 没有发生换组行为，直接更新
            LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                    .eq(ShortLinkDO::getGid, requestParam.getGid())
                    .eq(ShortLinkDO::getEnable, 1)
                    .set(Objects.equals(requestParam.getValidDateType(), PERMANENT.getType()), ShortLinkDO::getValidDate, null);
            ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                    .gid(requestParam.getGid())
                    .originUrl(requestParam.getOriginUrl())
                    .description(requestParam.getDescribe())
                    .validDateType(requestParam.getValidDateType())
                    .validDate(requestParam.getValidDate())
                    .build();
            baseMapper.update(shortLinkDO, updateWrapper);
        } else {
            // 发生了换组行为，需要先删再新增
        }
    }

    @SneakyThrows
    @Override
    public void redirectUrl(String shortUri, ServletRequest request, ServletResponse response) {
        String serverName = /* DOMAIN_PREFIX + */ request.getServerName();
        String fullShortUrl = StrBuilder.create(serverName).append("/").append(shortUri).toString();
        // 先查缓存，如果有就直接返回
        String originLink = stringRedisTemplate.opsForValue().get(String.format(GOTO_SHORT_LINK_KEY, fullShortUrl));
        if (StrUtil.isNotBlank(originLink)) {
            // 跳转前完成对应短链接的统计
            // TODO 加上gid
            shortLinkStatistics(fullShortUrl, null, request, response);
            ((HttpServletResponse) response).sendRedirect(originLink);
            return ;
        }
        // 查询第一层布隆过滤器，查询当前请求的 fullShortLink 是否存在，如果不存在，表明数据库中不存在，直接重定向到不存在页面
        if (!shortUriCreateCachePenetrationBloomFilter.contains(fullShortUrl)) {
            ((HttpServletResponse) response).sendRedirect(REDIRECT_TO_NOT_FOUND_URI);
            return;
        }
        // 如果上面的布隆过滤器中存在，查询第二层布隆过滤器，如果存在值，说明是误判，直接返回
        String gotoIsNullShortLinkKey = stringRedisTemplate.opsForValue().get(String.format(GOTO_IS_NULL_SHORT_LINK_KEY, fullShortUrl));
        if (StrUtil.isNotBlank(gotoIsNullShortLinkKey)) {
            ((HttpServletResponse) response).sendRedirect(REDIRECT_TO_NOT_FOUND_URI);
            return ;
        }
        // 前面都通过了，正常执行后续业务流程
        // 加锁
        RLock lock = redissonClient.getLock(String.format(LOCK_GOTO_SHORT_LINK_KEY, fullShortUrl));
        lock.lock();
        try {
            // 防止所有线程都来执行一边下面的流程，双重检锁
            originLink = stringRedisTemplate.opsForValue().get(String.format(GOTO_SHORT_LINK_KEY, fullShortUrl));
            if (StrUtil.isNotBlank(originLink)) {
                // 跳转前完成对应短链接的统计
                // TODO 加上gid
                shortLinkStatistics(fullShortUrl, null, request, response);
                ((HttpServletResponse) response).sendRedirect(originLink);
                return ;
            }
            // 先查路由表
            LambdaQueryWrapper<ShortLinkGoToDO> linkGotoQueryWrapper = Wrappers.lambdaQuery(ShortLinkGoToDO.class)
                    .eq(ShortLinkGoToDO::getFullShortUrl, fullShortUrl);
            ShortLinkGoToDO shortLinkGoToDO = shortLinkGoToService.getOne(linkGotoQueryWrapper);
            if (shortLinkGoToDO == null) {
                // 查询数据库中发现没有，将当前的 fullShortLink 加到第二层空值布隆过滤器中
                stringRedisTemplate.opsForValue().set(String.format(GOTO_IS_NULL_SHORT_LINK_KEY, fullShortUrl), "-", 30, TimeUnit.MINUTES);
                ((HttpServletResponse) response).sendRedirect(REDIRECT_TO_NOT_FOUND_URI);
                return;
            }
            // 再查短链接表
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getGid, shortLinkGoToDO.getGid())
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl)
                    .eq(ShortLinkDO::getEnable, 1);
            ShortLinkDO shortLinkDO = baseMapper.selectOne(queryWrapper);
            if (shortLinkDO == null || shortLinkDO.getValidDate().isBefore(LocalDateTime.now())) {
                // 如果查询数据库中发现不存在，或者存在，但是已经过期，将当前的 fullShortLink 加到第二层布隆过滤器中
                stringRedisTemplate.opsForValue().set(String.format(GOTO_IS_NULL_SHORT_LINK_KEY, fullShortUrl), "-", 30, TimeUnit.MINUTES);
                ((HttpServletResponse) response).sendRedirect(REDIRECT_TO_NOT_FOUND_URI);
                return;
            }
            stringRedisTemplate.opsForValue().set(
                    String.format(GOTO_SHORT_LINK_KEY, fullShortUrl),
                    shortLinkDO.getOriginUrl(),
                    getLinkCacheValidDate(shortLinkDO.getValidDate()),
                    TimeUnit.MILLISECONDS);
            // 跳转前完成对应短链接的统计
            shortLinkStatistics(fullShortUrl, shortLinkDO.getGid(), request, response);
            ((HttpServletResponse) response).sendRedirect(shortLinkDO.getOriginUrl());
        } finally {
            lock.unlock();
        }
    }

    /**
     * 短链接跳转时完成对应对链接的统计
     * @param request       请求
     * @param response      响应
     * @param fullShortUrl  完整短链接
     * @param gid           短链接分组标识
     */
    private void shortLinkStatistics(String fullShortUrl, String gid, ServletRequest request, ServletResponse response) {
        AtomicBoolean uvEmptyFlag = new AtomicBoolean();
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        try {
            // 利用 Runnable 的 run 方法由当前线程执行内部的内容，相当于分离了一个方法出来
            AtomicReference<String> uvFlag = new AtomicReference<>();
            Runnable addResponseCookieTask = () -> {
                // 当前名称 uv 的 Cookie 不存在，或者当前不存在 Cookie
                // 利用 Cookie 来判断是否是同一用户访问，该 Cookie 的保存时间为 1 个月
                uvFlag.set(UUID.fastUUID().toString());
                Cookie uvCookie = new Cookie("uv", uvFlag.get());
                uvCookie.setMaxAge(60 * 60 * 24 * 30);
                uvCookie.setPath(StrUtil.sub(fullShortUrl, fullShortUrl.lastIndexOf("/"), fullShortUrl.length()));
                ((HttpServletResponse) response).addCookie(uvCookie);
                // TODO 这种直接存 redis 的方式涉及到要存多久，短链接越来越多的时候，很可能发生影响，后续重构要进行修改
                stringRedisTemplate.opsForSet().add("short-link:statistics:uv:" + fullShortUrl, uvFlag.get());
                uvEmptyFlag.set(true);
            };
            if (ArrayUtil.isNotEmpty(cookies)) {
                Arrays.stream(cookies)
                        .filter(each -> Objects.equals(each.getName(), "uv"))
                        .findFirst()
                        .map(Cookie::getValue)
                        .ifPresentOrElse(each -> {
                            // 当前名称 uv 的 Cookie 存在，先将当前用户存储起来，然后判断当前用户 each 是否之前访问过该短链接
                            uvFlag.set(each);
                            Long uvAdded = stringRedisTemplate.opsForSet().add("short-link:statistics:uv:" + fullShortUrl, each);
                            uvEmptyFlag.set(uvAdded != null && uvAdded > 0);
                        }, addResponseCookieTask);

            } else {
                addResponseCookieTask.run();
            }
            // 获取当前用户 IP 地址
            String remoteAddr = LinkUtil.getRealIp((HttpServletRequest) request);
            // TODO 和前面的 UV 一样的问题，如何保证大数据量不会将内存撑爆
            Long uipAdded = stringRedisTemplate.opsForSet().add("short-link:statistics:uip:" + fullShortUrl, remoteAddr);
            boolean uipEmptyFlag = uipAdded != null && uipAdded > 0;
            if (StrUtil.isBlank(gid)) {
                LambdaQueryWrapper<ShortLinkGoToDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkGoToDO.class)
                        .eq(ShortLinkGoToDO::getFullShortUrl, fullShortUrl);
                ShortLinkGoToDO shortLinkGoToDO = shortLinkGoToService.getOne(queryWrapper);
                gid = shortLinkGoToDO.getGid();
            }
            int week = DateUtil.dayOfWeekEnum(new Date()).getIso8601Value();
            int hour = DateUtil.hour(new Date(), true);
            ShortLinkStatsDO statisticsDO = ShortLinkStatsDO.builder()
                    .pv(1)
                    .uv(uvEmptyFlag.get() ? 1 : 0)
                    .uip(uipEmptyFlag ? 1 : 0)
                    .hour(hour)
                    .weekday(week)
                    .fullShortUrl(fullShortUrl)
                    .gid(gid)
                    .date(new Date())
                    .build();
            shortLinkStatsMapper.shortLinkStatistics(statisticsDO);
            // 短链接访问地区统计
            Map<String, Object> localeParamMap = new HashMap<>();
            localeParamMap.put("key", statisticsLocaleAmapKey);
            localeParamMap.put("ip", remoteAddr);
            String localeResultStr = HttpUtil.get(AMAP_REMOTE_URL, localeParamMap);
            JSONObject localeResultObj = JSON.parseObject(localeResultStr);
            String infocode = localeResultObj.getString("infocode");
            ShortLinkLocaleStatisticsDO localeStatisticsDO;
            if (StrUtil.isNotBlank(infocode) && StrUtil.equals(infocode, "10000")) {
                String province = localeResultObj.getString("province");
                boolean unknownFlag = StrUtil.equals(province, "[]");
                localeStatisticsDO = ShortLinkLocaleStatisticsDO.builder()
                        .fullShortUrl(fullShortUrl)
                        .province(unknownFlag ? "未知" : province)
                        .city(unknownFlag ? "未知" : localeResultObj.getString("city"))
                        .adcode(unknownFlag ? "未知" : localeResultObj.getString("adcode"))
                        .country("中国")
                        .gid(gid)
                        .date(new Date())
                        .build();
                shortLinkStatsLocaleMapper.shortLinkLocaleStatistics(localeStatisticsDO);
            }
            // 操作系统访问统计
            String os = LinkUtil.getOs((HttpServletRequest) request);
            ShortLinkOsStatisticsDO shortLinkOsStatisticsDO = ShortLinkOsStatisticsDO.builder()
                    .os(os)
                    .fullShortUrl(fullShortUrl)
                    .cnt(1)
                    .gid(gid)
                    .date(new Date())
                    .build();
            shortLinkOsStatisticsMapper.shortLinkOsStatistics(shortLinkOsStatisticsDO);
            // 浏览器访问统计
            String browser = LinkUtil.getBrowser((HttpServletRequest) request);
            ShortLinkStatsBrowserDO shortLinkStatsBrowserDO = ShortLinkStatsBrowserDO.builder()
                    .browser(browser)
                    .fullShortUrl(fullShortUrl)
                    .cnt(1)
                    .gid(gid)
                    .date(new Date())
                    .build();
            shortLinkStatsBrowserMapper.shortLinkBrowserStatistics(shortLinkStatsBrowserDO);
            // 添加短链接访问日志监控数据
            ShortLinkAccessLogsDO shortLinkAccessLogsDO = ShortLinkAccessLogsDO.builder()
                    .user(uvFlag.get())
                    .ip(remoteAddr)
                    .browser(browser)
                    .os(os)
                    .fullShortUrl(fullShortUrl)
                    .gid(gid)
                    .build();
            shortLinkAccessLogsMapper.insert(shortLinkAccessLogsDO);
            // 添加短链接访问设备监控数据
            ShortLinkDeviceStatisticsDO shortLinkDeviceStatisticsDO = ShortLinkDeviceStatisticsDO.builder()
                    .device(LinkUtil.getDevice((HttpServletRequest) request))
                    .fullShortUrl(fullShortUrl)
                    .cnt(1)
                    .gid(gid)
                    .date(new Date())
                    .build();
            shortLinkDeviceStatisticsMapper.shortLinkDeviceStatistics(shortLinkDeviceStatisticsDO);
            // 添加短链接访问网络监控数据
            ShortLinkNetworkStatisticsDO shortLinkNetworkStatisticsDO = ShortLinkNetworkStatisticsDO.builder()
                    .network(LinkUtil.getNetwork(remoteAddr))
                    .fullShortUrl(fullShortUrl)
                    .cnt(1)
                    .gid(gid)
                    .date(new Date())
                    .build();
            shortLinkNetworkStatisticsMapper.shortLinkNetworkStatistics(shortLinkNetworkStatisticsDO);
        } catch (Throwable e) {
            log.error("短链接跳转失败", e);
        }
    }

    @SneakyThrows
    @Override
    public String getTitleByUrl(String url) {
        URL targetUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Document document = Jsoup.connect(url).get();
            return document.title();
        }
        return "Error while fetching title.";
    }
}
