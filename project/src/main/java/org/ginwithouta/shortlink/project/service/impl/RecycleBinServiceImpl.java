package org.ginwithouta.shortlink.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dao.mapper.ShortLinkMapper;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.ginwithouta.shortlink.project.service.RecycleBinService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.GOTO_SHORT_LINK_KEY;

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
                .eq(ShortLinkDO::getEnable, 1);
        ShortLinkDO updatedDO = ShortLinkDO.builder()
                .enable(0)
                .build();
        baseMapper.update(updatedDO, updateWrapper);
        // 当前短链接被移动到回收站之后，需要禁用当前短链接
        stringRedisTemplate.delete(String.format(GOTO_SHORT_LINK_KEY, requestParam.getFullShortUrl()));
    }
}
