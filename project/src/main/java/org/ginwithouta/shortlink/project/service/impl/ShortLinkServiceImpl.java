package org.ginwithouta.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.project.common.convention.exception.ClientException;
import org.ginwithouta.shortlink.project.common.convention.exception.ServiceException;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dao.mapper.ShortLinkMapper;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.project.service.ShortLinkService;
import org.ginwithouta.shortlink.project.toolkit.HashUtil;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import static org.ginwithouta.shortlink.project.common.enums.ShortLinkErrorCodeEnums.*;

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
        try {
            int inserted = baseMapper.insert(shortLinkDO);
            if (inserted < 1) {
                    throw new ServiceException(SHORT_LINK_CREATE_FAIL);
            }
        } catch (DuplicateKeyException ex) {
            // 多线程情况下，可能有多个线程获取到同一个短链接，并判空
            log.warn("短链接：并发异常，{} 重复入库", fullShortLinkUrl);
            throw new ServiceException(SHORT_LINK_BLOOM_FAIL);
        }
        shortUriCreateCachePenetrationBloomFilter.add(fullShortLinkUrl);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(shortLinkDO.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLinkList(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnable, 1);
        IPage<ShortLinkDO> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
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
}
