package org.ginwithouta.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.admin.dao.entity.GroupDO;
import org.ginwithouta.shortlink.admin.dao.mapper.GroupMapper;
import org.ginwithouta.shortlink.admin.service.GroupService;
import org.ginwithouta.shortlink.admin.toolkit.RandomGenerator;
import org.springframework.stereotype.Service;

/**
 * @Package : org.ginwithouta.shortlink.admin.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {
        String gid = RandomGenerator.generateRandomCode();
        while (hasGid(gid)) {
            gid = RandomGenerator.generateRandomCode();
        }
        GroupDO groupDO = GroupDO.builder()
                .name(groupName)
                .gid(gid)
                .build();
        baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> wrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                // TODO: 设置用户名
                .eq(GroupDO::getUsername, null);
        return baseMapper.selectOne(wrapper) != null;

    }
}
