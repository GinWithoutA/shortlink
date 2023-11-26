package org.ginwithouta.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.project.common.convention.exception.ClientException;
import org.ginwithouta.shortlink.project.common.convention.exception.ServiceException;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkGoToDO;
import org.ginwithouta.shortlink.project.dao.mapper.ShortLinkGoToMapper;
import org.ginwithouta.shortlink.project.dao.mapper.ShortLinkMapper;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.project.service.ShortLinkService;
import org.ginwithouta.shortlink.project.toolkit.HashUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.*;
import static org.ginwithouta.shortlink.project.common.constant.ShortLinkConstant.DOMAIN_PREFIX;
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

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;
    private final ShortLinkGoToMapper shortLinkGoToMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final RedissonClient redissonClient;

    private static final int MAX_GENERATE_TIMES = 100;

    @Override
    public ShortLinkCreateRespDTO createShorLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        String fullShortLinkUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setFullShortUrl(fullShortLinkUrl);
        shortLinkDO.setShortUri(shortLinkSuffix);
        // TODO 恶意请求有风险，后续要改成异步的
        shortLinkDO.setFavicon(getFavicon(requestParam.getOriginUrl()));
        // 创建短链接的时候同时插入路由记录
        ShortLinkGoToDO shortLinkGoToDO = ShortLinkGoToDO.builder()
                .gid(requestParam.getGid())
                .fullShortUrl(fullShortLinkUrl)
                .build();
        try {
            int inserted = baseMapper.insert(shortLinkDO);
            inserted += shortLinkGoToMapper.insert(shortLinkGoToDO);
            if (inserted < 2) {
                    throw new ServiceException(SHORT_LINK_CREATE_FAIL);
            }
        } catch (DuplicateKeyException ex) {
            // 多线程情况下，可能有多个线程获取到同一个短链接，并判空
            log.warn("短链接：并发异常，{} 重复入库", fullShortLinkUrl);
            throw new ServiceException(SHORT_LINK_BLOOM_FAIL);
        }
        // 创建的短链接直接放到 Redis 缓存中
        stringRedisTemplate.opsForValue().set(
                String.format(GOTO_SHORT_LINK_KEY, fullShortLinkUrl),
                requestParam.getOriginUrl(),
                getLinkCacheValidDate(requestParam.getValidDate()), TimeUnit.MILLISECONDS);
        shortUriCreateCachePenetrationBloomFilter.add(fullShortLinkUrl);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(shortLinkDO.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    /**
     * 生成 BASE62 编码生成短链接后缀
     * @param requestParam 生成短链接入参
     * @return 短链接后缀
     */
    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        int customGenerateCount = 1;
        String shortUri, originUrl;
        while (true) {
            originUrl = StrBuilder.create(requestParam.getOriginUrl())
                    .append(System.currentTimeMillis())
                    .toString();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCreateCachePenetrationBloomFilter.contains(StrBuilder.create(requestParam.getDomain())
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
                    .description(requestParam.getDescription())
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
        String serverName = DOMAIN_PREFIX + request.getServerName();
        String fullShortUrl = StrBuilder.create(serverName).append("/").append(shortUri).toString();
        // 先查缓存，如果有就直接返回
        String originLink = stringRedisTemplate.opsForValue().get(String.format(GOTO_SHORT_LINK_KEY, fullShortUrl));
        if (StrUtil.isNotBlank(originLink)) {
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
                ((HttpServletResponse) response).sendRedirect(originLink);
                return ;
            }
            // 先查路由表
            LambdaQueryWrapper<ShortLinkGoToDO> linkGotoQueryWrapper = Wrappers.lambdaQuery(ShortLinkGoToDO.class)
                    .eq(ShortLinkGoToDO::getFullShortUrl, fullShortUrl);
            ShortLinkGoToDO shortLinkGoToDO = shortLinkGoToMapper.selectOne(linkGotoQueryWrapper);
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
            ((HttpServletResponse) response).sendRedirect(shortLinkDO.getOriginUrl());
        } finally {
            lock.unlock();
        }
    }
}
