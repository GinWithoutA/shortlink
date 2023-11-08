package org.ginwithouta.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.admin.common.convention.exception.ClientException;
import org.ginwithouta.shortlink.admin.common.enums.UserErrorCodeEnums;
import org.ginwithouta.shortlink.admin.dao.entity.UserDO;
import org.ginwithouta.shortlink.admin.dao.mapper.UserMapper;
import org.ginwithouta.shortlink.admin.dto.resp.UserRespDTO;
import org.ginwithouta.shortlink.admin.service.UserService;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Package : org.ginwithouta.shortlink.admin.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 用户接口实现层
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnums.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }
}
