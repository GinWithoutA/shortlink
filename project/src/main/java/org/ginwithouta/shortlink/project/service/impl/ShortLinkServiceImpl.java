package org.ginwithouta.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
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
import org.ginwithouta.shortlink.project.config.GotoDomainWhiteListConfig;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkGoToDO;
import org.ginwithouta.shortlink.project.dao.mapper.*;
import org.ginwithouta.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateBatchReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateBatchRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.project.mq.producer.ShortLinkStatsSaveProducer;
import org.ginwithouta.shortlink.project.service.ShortLinkGoToService;
import org.ginwithouta.shortlink.project.service.ShortLinkService;
import org.ginwithouta.shortlink.project.service.ShortLinkStatsTodayService;
import org.ginwithouta.shortlink.project.toolkit.HashUtil;
import org.ginwithouta.shortlink.project.toolkit.LinkUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.*;
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
    private final ShortLinkOsStatsMapper shortLinkOsStatsMapper;
    private final RBloomFilter<String> shortLinkUriCachePenetrationBloomFilter;
    private final ShortLinkDeviceStatsMapper shortLinkDeviceStatsMapper;
    private final ShortLinkLocaleStatsMapper shortLinkLocaleStatsMapper;
    private final ShortLinkNetworkStatsMapper shortLinkNetworkStatsMapper;
    private final ShortLinkBrowserStatsMapper shortLinkBrowserStatsMapper;
    private final ShortLinkStatsTodayService shortLinkTodayStatsService;
    private final ShortLinkGoToMapper shortLinkGoToMapper;
    private final ShortLinkStatsSaveProducer shortLinkStatsSaveProducer;
    private final GotoDomainWhiteListConfig gotoDomain;

    @Value("${short-link.domain.default}")
    private String defaultDomain;

    private static final int MAX_GENERATE_TIMES = 100;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ShortLinkCreateRespDTO createShorLink(ShortLinkCreateReqDTO requestParam) {
        verificationWShiteList(requestParam.getOriginUrl());
        String shortLinkSuffix = generateSuffix(requestParam.getOriginUrl(), requestParam.getDomain());
        String fullShortLinkUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setFullShortUrl(fullShortLinkUrl);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setDescription(requestParam.getDescribe());
        shortLinkDO.setDomain(requestParam.getDomain());
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
            // 首先判断是否存在布隆过滤器，如果不存在直接新增
            if (!shortLinkUriCachePenetrationBloomFilter.contains(fullShortLinkUrl)) {
                shortLinkUriCachePenetrationBloomFilter.add(fullShortLinkUrl);
            }
            log.warn("短链接 {} 重复入库", fullShortLinkUrl);
            throw new ServiceException(SHORT_LINK_BLOOM_FAIL);
        }
        // 创建的短链接直接放到 Redis 缓存中
        stringRedisTemplate.opsForValue().set(
                String.format(REDIS_GOTO_SHORT_LINK_KEY, shortLinkDO.getFullShortUrl()),
                requestParam.getOriginUrl(),
                getLinkCacheValidDate(requestParam.getValidDate()), TimeUnit.MILLISECONDS);
        shortLinkUriCachePenetrationBloomFilter.add(shortLinkDO.getFullShortUrl());
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
            shortLinkDO.setDomain(requestParam.getDomain());
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
                    String.format(REDIS_GOTO_SHORT_LINK_KEY, each.getFullShortUrl()),
                    each.getOriginUrl(),
                    getLinkCacheValidDate(requestParam.getValidDate()), TimeUnit.MILLISECONDS);
            shortLinkUriCachePenetrationBloomFilter.add(each.getFullShortUrl());
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
            // 高并发场景中，使用 System.currentTimeMillis() 很容出现生成的 URI 相同的情况，如果都是同一原始连接，很可能生成的都是相同的 URI
            originUrl = StrBuilder.create(originUrl)
                    .append(UUID.randomUUID().toString())
                    .toString();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortLinkUriCachePenetrationBloomFilter.contains(StrBuilder.create(domain)
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
    private String getFavicon(String url) {
        try {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLinkList(ShortLinkPageReqDTO requestParam) {
        IPage<ShortLinkDO> resultPage = baseMapper.pageShortLink(requestParam);
        return resultPage.convert(each -> {
            ShortLinkPageRespDTO bean = BeanUtil.toBean(each, ShortLinkPageRespDTO.class);
            bean.setDescribe(each.getDescription());
            return bean;
        });
    }

    @Override
    public List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParams) {
        QueryWrapper<ShortLinkDO> queryWrapper = Wrappers.query(new ShortLinkDO()).select("gid, count(*) as shortLinkCount")
                .in("gid", requestParams)
                .eq("enable_status", 1)
                .groupBy("gid");
        List<Map<String, Object>> resultMap = baseMapper.selectMaps(queryWrapper);
        return BeanUtil.copyToList(resultMap, ShortLinkGroupCountQueryRespDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateShortLink(ShortLinkUpdateReqDTO requestParam) {
        verificationWShiteList(requestParam.getOriginUrl());
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getOriginGid())
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getEnableStatus, 1);
        ShortLinkDO selectShortLinkDO = baseMapper.selectOne(queryWrapper);
        if (selectShortLinkDO == null) {
            throw new ClientException(SHORT_LINK_NOT_EXIST);
        }
        if (Objects.equals(selectShortLinkDO.getGid(), requestParam.getGid())) {
            /*
             * 当前没有发生分组被修改的行为，直接进行数据的更新
             */
            LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                    .eq(ShortLinkDO::getGid, requestParam.getGid())
                    .eq(ShortLinkDO::getEnableStatus, 1)
                    .set(Objects.equals(requestParam.getValidDateType(), PERMANENT.getType()), ShortLinkDO::getValidDate, null);
            ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                    .domain(selectShortLinkDO.getDomain())
                    .shortUri(selectShortLinkDO.getShortUri())
                    .favicon(selectShortLinkDO.getFavicon())
                    .createdType(selectShortLinkDO.getCreatedType())
                    .gid(requestParam.getGid())
                    .originUrl(requestParam.getOriginUrl())
                    .description(requestParam.getDescribe())
                    .validDateType(requestParam.getValidDateType())
                    .validDate(requestParam.getValidDate())
                    .build();
            baseMapper.update(shortLinkDO, updateWrapper);
        } else {
            /*
             * 当前发生了分组信息修改的行为
             */
            RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(String.format(REDIS_LOCK_GID_UPDATE_KEY, requestParam.getFullShortUrl()));
            RLock rLock = readWriteLock.writeLock();
            rLock.lock();
            try {
                LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                        .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                        .eq(ShortLinkDO::getGid, requestParam.getGid())
                        .eq(ShortLinkDO::getEnableStatus, 1)
                        .eq(ShortLinkDO::getDelTime, 0L);
                ShortLinkDO delShortLinkDO = new ShortLinkDO();
                delShortLinkDO.setDelTime(System.currentTimeMillis());
                delShortLinkDO.setDelFlag(1);
                baseMapper.update(delShortLinkDO, updateWrapper);
                ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                        .domain(defaultDomain)
                        .originUrl(requestParam.getOriginUrl())
                        .gid(requestParam.getGid())
                        .createdType(selectShortLinkDO.getCreatedType())
                        .validDateType(requestParam.getValidDateType())
                        .validDate(requestParam.getValidDate())
                        .description(requestParam.getDescribe())
                        .shortUri(selectShortLinkDO.getShortUri())
                        .enableStatus(selectShortLinkDO.getEnableStatus())
                        .totalPv(selectShortLinkDO.getTotalPv())
                        .totalUv(selectShortLinkDO.getTotalUv())
                        .totalUip(selectShortLinkDO.getTotalUip())
                        .fullShortUrl(selectShortLinkDO.getFullShortUrl())
                        .favicon(getFavicon(requestParam.getOriginUrl()))
                        .delTime(0L)
                        .build();
                baseMapper.insert(shortLinkDO);
                /*
                 * 短链接路由信息修改(删除原有路由信息，新增路由信息)
                 */
                LambdaQueryWrapper<ShortLinkGoToDO> shortLinkGoToDOLambdaQueryWrapper = Wrappers.lambdaQuery(ShortLinkGoToDO.class)
                        .eq(ShortLinkGoToDO::getFullShortUrl, requestParam.getFullShortUrl())
                        .eq(ShortLinkGoToDO::getGid, selectShortLinkDO.getGid());
                ShortLinkGoToDO shortLinkGoToDO = shortLinkGoToMapper.selectOne(shortLinkGoToDOLambdaQueryWrapper);
                shortLinkGoToMapper.delete(shortLinkGoToDOLambdaQueryWrapper);
                shortLinkGoToDO.setGid(requestParam.getGid());
                shortLinkGoToDO.setId(null);
                shortLinkGoToMapper.insert(shortLinkGoToDO);
            } finally {
                rLock.unlock();
            }
        }
        // 如果发生了有效期的变更，需要删除原有的 redis 记录，当再次访问的时候，直接让他去数据库加载获取最新的日期就可以了
        if (!Objects.equals(selectShortLinkDO.getValidDateType(), requestParam.getValidDateType()) || !Objects.equals(selectShortLinkDO.getValidDate(), requestParam.getValidDate())) {
            stringRedisTemplate.delete(String.format(REDIS_GOTO_SHORT_LINK_KEY, requestParam.getFullShortUrl()));
            if (Objects.equals(requestParam.getValidDateType(), PERMANENT.getType()) || requestParam.getValidDate().isBefore(LocalDateTime.now())) {
                stringRedisTemplate.delete(String.format(REDIS_GOTO_SHORT_LINK_IS_NULL_KEY, requestParam.getFullShortUrl()));
            }
        }
    }

    @SneakyThrows
    @Override
    public void redirectUrl(String shortUri, ServletRequest request, ServletResponse response) {
        String serverName = request.getServerName();
        String fullShortUrl = StrBuilder.create(serverName).append("/").append(shortUri).toString();
        // 先查缓存，如果有就直接返回
        String originLink = stringRedisTemplate.opsForValue().get(String.format(REDIS_GOTO_SHORT_LINK_KEY, fullShortUrl));
        if (StrUtil.isNotBlank(originLink)) {
            // 跳转前完成对应短链接的统计，此时由于直接从 redis 中获取，没有 gid
            ShortLinkStatsRecordDTO statsRecord = buildLinkStatsRecordAndSetUser(fullShortUrl, request, response);
            shortLinkStats(fullShortUrl, null, statsRecord);
            ((HttpServletResponse) response).sendRedirect(originLink);
            return ;
        }
        // 查询第一层布隆过滤器，查询当前请求的 fullShortLink 是否存在，如果不存在，表明数据库中不存在，直接重定向到不存在页面
        if (!shortLinkUriCachePenetrationBloomFilter.contains(fullShortUrl)) {
            ((HttpServletResponse) response).sendRedirect(REDIRECT_TO_NOT_FOUND_URI);
            return;
        }
        // 如果上面的布隆过滤器中存在，查询第二层缓存，如果存在值，说明当前短链接已经失效或者不存在，直接返回
        String gotoIsNullShortLinkKey = stringRedisTemplate.opsForValue().get(String.format(REDIS_GOTO_SHORT_LINK_IS_NULL_KEY, fullShortUrl));
        if (StrUtil.isNotBlank(gotoIsNullShortLinkKey)) {
            ((HttpServletResponse) response).sendRedirect(REDIRECT_TO_NOT_FOUND_URI);
            return ;
        }
        // 前面都通过了，正常执行后续业务流程
        // 加锁
        RLock lock = redissonClient.getLock(String.format(REDIS_LOCK_GOTO_SHORT_LINK_KEY, fullShortUrl));
        lock.lock();
        try {
            // 防止所有线程都来执行一边下面的流程，双重检锁
            originLink = stringRedisTemplate.opsForValue().get(String.format(REDIS_GOTO_SHORT_LINK_KEY, fullShortUrl));
            if (StrUtil.isNotBlank(originLink)) {
                ShortLinkStatsRecordDTO statsRecord = buildLinkStatsRecordAndSetUser(fullShortUrl, request, response);
                shortLinkStats(fullShortUrl, null, statsRecord);
                ((HttpServletResponse) response).sendRedirect(originLink);
                return ;
            }
            // 先查路由表
            LambdaQueryWrapper<ShortLinkGoToDO> linkGotoQueryWrapper = Wrappers.lambdaQuery(ShortLinkGoToDO.class)
                    .eq(ShortLinkGoToDO::getFullShortUrl, fullShortUrl);
            ShortLinkGoToDO shortLinkGoToDO = shortLinkGoToService.getOne(linkGotoQueryWrapper);
            if (shortLinkGoToDO == null) {
                // 查询数据库中发现没有，将当前的 fullShortLink 加到第二层空值过滤器中
                stringRedisTemplate.opsForValue().set(String.format(REDIS_GOTO_SHORT_LINK_IS_NULL_KEY, fullShortUrl), "-", 30, TimeUnit.MINUTES);
                ((HttpServletResponse) response).sendRedirect(REDIRECT_TO_NOT_FOUND_URI);
                return;
            }
            // 再查短链接表
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getGid, shortLinkGoToDO.getGid())
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl)
                    .eq(ShortLinkDO::getEnableStatus, 1);
            ShortLinkDO shortLinkDO = baseMapper.selectOne(queryWrapper);
            // 有效期可能为空
            if (shortLinkDO == null || (shortLinkDO.getValidDate() != null && shortLinkDO.getValidDate().isBefore(LocalDateTime.now()))) {
                // 如果查询数据库中发现不存在，或者存在，但是已经过期，将当前的 fullShortLink 加到第二层布隆过滤器中
                stringRedisTemplate.opsForValue().set(String.format(REDIS_GOTO_SHORT_LINK_IS_NULL_KEY, fullShortUrl), "-", 30, TimeUnit.MINUTES);
                ((HttpServletResponse) response).sendRedirect(REDIRECT_TO_NOT_FOUND_URI);
                return;
            }
            stringRedisTemplate.opsForValue().set(
                    String.format(REDIS_GOTO_SHORT_LINK_KEY, fullShortUrl),
                    shortLinkDO.getOriginUrl(),
                    getLinkCacheValidDate(shortLinkDO.getValidDate()),
                    TimeUnit.MILLISECONDS);
            // 跳转前完成对应短链接的统计
            ShortLinkStatsRecordDTO statsRecord = buildLinkStatsRecordAndSetUser(fullShortUrl, request, response);
            shortLinkStats(fullShortUrl, null, statsRecord);
            ((HttpServletResponse) response).sendRedirect(shortLinkDO.getOriginUrl());
        } finally {
            lock.unlock();
        }
    }

    /**
     * 短链接跳转时完成对应对链接的统计
     */
    @Override
    public void shortLinkStats(String fullShortUrl, String gid, ShortLinkStatsRecordDTO statsRecord) {
        Map<String, String> producerMap = new HashMap<>();
        producerMap.put("fullShortUrl", fullShortUrl);
        producerMap.put("gid", gid);
        producerMap.put("statsRecord", JSON.toJSONString(statsRecord));
        shortLinkStatsSaveProducer.send(producerMap);
    }

    private ShortLinkStatsRecordDTO buildLinkStatsRecordAndSetUser(String fullShortUrl, ServletRequest request, ServletResponse response) {
        /*
         * 短链接监控之基础信息监控
         */
        AtomicBoolean uvFirstFlag = new AtomicBoolean();
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        AtomicReference<String> uvFlag = new AtomicReference<>();
        Runnable addResponseCookieTask = () -> {
            // 当前名称 uv 的 Cookie 不存在，或者当前不存在 Cookie
            // 利用 Cookie 来判断是否是同一用户访问，该 Cookie 的保存时间为 1 个月
            uvFlag.set(UUID.fastUUID().toString());
            Cookie uvCookie = new Cookie("uv", uvFlag.get());
            uvCookie.setMaxAge(60 * 60 * 24 * 30);
            // 指定 Cookie 的适用范围
            uvCookie.setPath(StrUtil.sub(fullShortUrl, fullShortUrl.lastIndexOf("/"), fullShortUrl.length()));
            ((HttpServletResponse) response).addCookie(uvCookie);
            stringRedisTemplate.opsForSet().add(StrUtil.format(REDIS_SHORT_LINK_STATS_UV_KEY, fullShortUrl), uvFlag.get());
            uvFirstFlag.set(Boolean.TRUE);
        };
        if (ArrayUtil.isNotEmpty(cookies)) {
            Arrays.stream(cookies)
                    .filter(each -> Objects.equals(each.getName(), "uv"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .ifPresentOrElse(each -> {
                        // 当前名称 uv 的 Cookie 存在，先将当前用户存储起来，然后判断当前用户 each 是否之前访问过该短链接
                        uvFlag.set(each);
                        Long uvAdded = stringRedisTemplate.opsForSet().add(StrUtil.format(REDIS_SHORT_LINK_STATS_UV_KEY, fullShortUrl), each);
                        uvFirstFlag.set(uvAdded != null && uvAdded > 0L);
                    }, addResponseCookieTask);

        } else {
            // 认为用户第一次访问，直接加 Cookie 就行
            addResponseCookieTask.run();
        }
        String remoteAddr = LinkUtil.getRealIp((HttpServletRequest) request);
        String os = LinkUtil.getOs((HttpServletRequest) request);
        String browser = LinkUtil.getBrowser((HttpServletRequest) request);
        String device = LinkUtil.getDevice((HttpServletRequest) request);
        String network = LinkUtil.getNetwork(remoteAddr);
        // TODO 和前面的 UV 一样的问题，如何保证大数据量不会将内存撑爆
        Long uipAdded = stringRedisTemplate.opsForSet().add(StrUtil.format(REDIS_SHORT_LINK_STATS_UIP_KEY, fullShortUrl), remoteAddr);
        boolean uipFirstFlag = uipAdded != null && uipAdded > 0;
        return ShortLinkStatsRecordDTO.builder()
                .fullShortUrl(fullShortUrl)
                .uv(uvFlag.get())
                .uvFirstFlag(uvFirstFlag.get())
                .uipFirstFlag(uipFirstFlag)
                .remoteAddr(remoteAddr)
                .os(os)
                .browser(browser)
                .device(device)
                .network(network)
                .build();
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

    private void verificationWShiteList(String originUrl) {
        Boolean enable = gotoDomain.getEnable();
        if (enable == null || !enable) {
            return;
        }
        String domain = LinkUtil.extractDomain(originUrl);
        if (StrUtil.isNotBlank(domain)) {
            throw new ClientException(SHORT_LINK_BLACK_LIST);
        }
        List<String> details = gotoDomain.getDetails();
        if (!details.contains(domain)) {
            // TODO 后续返回具体可以跳转的链接
            throw new ClientException(SHORT_LINK_BLACK_LIST);
        }
    }
}
