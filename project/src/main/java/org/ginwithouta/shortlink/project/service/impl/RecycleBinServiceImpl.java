package org.ginwithouta.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dao.mapper.ShortLinkMapper;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinPageReqDTO;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinRemoveReqDTO;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinRestoreReqDTO;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.project.service.RecycleBinService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.*;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 回收站管理服务层接口实现
 */
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements RecycleBinService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void save(RecycleBinSaveReqDTO requestParam) {
        LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 1);
        ShortLinkDO updatedDO = ShortLinkDO.builder()
                .enableStatus(0)
                .build();
        baseMapper.update(updatedDO, updateWrapper);
        // 当前短链接被移动到回收站之后，需要禁用当前短链接
        stringRedisTemplate.delete(String.format(REDIS_GOTO_SHORT_LINK_KEY, requestParam.getFullShortUrl()));
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageRecycleBinList(RecycleBinPageReqDTO requestParam) {
        IPage<ShortLinkDO> resultPage = baseMapper.pageRecycleBinShortLink(requestParam);
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }

    @Override
    public void restore(RecycleBinRestoreReqDTO requestParam) {
        LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0);
        ShortLinkDO updatedDO = ShortLinkDO.builder()
                .enableStatus(1)
                .build();
        baseMapper.update(updatedDO, updateWrapper);
        // 当前短链接被移动到回收站之后，需要禁用当前短链接
        stringRedisTemplate.delete(String.format(REDIS_GOTO_SHORT_LINK_IS_NULL_KEY, requestParam.getFullShortUrl()));
        /*
         * 这里可以不用做缓存的预热，一个从回收站中移除的短链接一边来说不会有太大的访问量，可以直接使用缓存击穿的方式来进行处理
         */
    }

    @Override
    public void remove(RecycleBinRemoveReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0);
        baseMapper.delete(queryWrapper);
    }
}
