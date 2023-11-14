package org.ginwithouta.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import org.ginwithouta.shortlink.admin.remote.ShortLinkRemoteService;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.ginwithouta.shortlink.admin.service.GroupService;
import org.ginwithouta.shortlink.admin.toolkit.RandomGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.ginwithouta.shortlink.admin.common.enums.ShortLinkGroupErrorCodeEnums.GROUP_DELETE_FAIL;

/**
 * @Package : org.ginwithouta.shortlink.admin.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    /**
     * TODO 后续重构为 Spring Cloud
     * 远程调用接口
     */
    private final ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {};

    @Override
    public void saveGroup(String groupName) {
        String gid = RandomGenerator.generateRandomCode();
        while (hasGid(gid)) {
            gid = RandomGenerator.generateRandomCode();
        }
        GroupDO groupDO = GroupDO.builder()
                .name(groupName)
                .gid(gid)
                .username(UserContext.getUsername())
                .sortOrder(0)
                .build();
        baseMapper.insert(groupDO);
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

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> wrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                // TODO: 设置用户名
                .eq(GroupDO::getUsername, UserContext.getUsername());
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
