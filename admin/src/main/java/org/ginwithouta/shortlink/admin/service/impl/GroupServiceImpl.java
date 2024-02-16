package org.ginwithouta.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.admin.biz.user.UserContext;
import org.ginwithouta.shortlink.admin.common.convention.exception.ClientException;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.common.database.BaseDO;
import org.ginwithouta.shortlink.admin.dao.entity.GroupDO;
import org.ginwithouta.shortlink.admin.dao.mapper.GroupMapper;
import org.ginwithouta.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.ginwithouta.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.ginwithouta.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.ginwithouta.shortlink.admin.remote.service.ShortLinkRemoteService;
import org.ginwithouta.shortlink.admin.service.GroupService;
import org.ginwithouta.shortlink.admin.toolkit.RandomGenerator;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.ginwithouta.shortlink.admin.common.constant.RedisCacheConstant.REDIS_LOCK_GROUP_CREATE_KEY;
import static org.ginwithouta.shortlink.admin.common.enums.ShortLinkGroupErrorCodeEnums.GROUP_CREATE_EXCEED_MAX;
import static org.ginwithouta.shortlink.admin.common.enums.ShortLinkGroupErrorCodeEnums.GROUP_DELETE_FAIL;

/**
 * @Package : org.ginwithouta.shortlink.admin.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    private final RedissonClient redissonClient;

    @Value("${short-link.group.max-num}")
    private Integer groupMaxNum;

    /**
     * TODO 后续重构为 Spring Cloud
     * 远程调用接口
     */
    private final ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {};

    @Override
    public void saveGroup(String groupName) {
        this.saveGroup(UserContext.getUsername(), groupName);
    }

    @Override
    public void saveGroup(String username, String groupName) {
        RLock lock = redissonClient.getLock(String.format(REDIS_LOCK_GROUP_CREATE_KEY, username, groupName));
        lock.lock();
        try {
            LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class).eq(GroupDO::getUsername, username);
            List<GroupDO> groupDOS = baseMapper.selectList(queryWrapper);
            if (CollUtil.isNotEmpty(groupDOS) && groupDOS.size() == groupMaxNum) {
                throw new ClientException(GROUP_CREATE_EXCEED_MAX);
            }
            String gid = RandomGenerator.generateRandomCode();
            while (hasGid(username, gid)) {
                gid = RandomGenerator.generateRandomCode();
            }
            GroupDO groupDO = GroupDO.builder()
                    .name(groupName)
                    .gid(gid)
                    .username(username)
                    .sortOrder(0)
                    .build();
            baseMapper.insert(groupDO);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, BaseDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
        List<ShortLinkGroupRespDTO> shortLinkGroupRespDTOs = BeanUtil.copyToList(groupDOList, ShortLinkGroupRespDTO.class);
        Result<List<ShortLinkGroupCountQueryRespDTO>> listResult
                = shortLinkRemoteService.listGroupShortLinkCount(groupDOList.stream()
                    .map(GroupDO::getGid)
                    .collect(Collectors.toList()));
        shortLinkGroupRespDTOs.forEach(each -> {
            Optional<ShortLinkGroupCountQueryRespDTO> shortLinkGroupCountQueryRespDTO = listResult.getData().stream()
                    .filter(item -> Objects.equals(item.getGid(), each.getGid()))
                    .findFirst();
            shortLinkGroupCountQueryRespDTO.ifPresent(item -> each.setShortLinkCount(shortLinkGroupCountQueryRespDTO.get().getShortLinkCount()));
        });
        return shortLinkGroupRespDTOs;
    }

    private boolean hasGid(String username, String gid) {
        LambdaQueryWrapper<GroupDO> wrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, Optional.ofNullable(username).orElse(UserContext.getUsername()));
        return baseMapper.selectOne(wrapper) != null;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, requestParam.getGid());
        GroupDO groupDO = GroupDO.builder().name(requestParam.getName()).build();
        baseMapper.update(groupDO, queryWrapper);
    }

    @Override
    public void deleteGroup(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, gid);
        GroupDO groupDO = baseMapper.selectOne(queryWrapper);
        if (groupDO == null) {
            throw new ClientException(GROUP_DELETE_FAIL);
        }
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParams) {
        requestParams.forEach(requestParam -> {
            GroupDO groupDO = GroupDO.builder()
                    .sortOrder(requestParam.getSortOrder())
                    .build();
            LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getGid, requestParam.getGid());
            baseMapper.update(groupDO, queryWrapper);
        });
    }
}
