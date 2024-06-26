package org.ginwithouta.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.admin.common.convention.exception.ClientException;
import org.ginwithouta.shortlink.admin.common.enums.UserErrorCodeEnums;
import org.ginwithouta.shortlink.admin.dao.entity.UserDO;
import org.ginwithouta.shortlink.admin.dao.mapper.UserMapper;
import org.ginwithouta.shortlink.admin.dto.req.UserLoginReqDTO;
import org.ginwithouta.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.ginwithouta.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.ginwithouta.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.ginwithouta.shortlink.admin.dto.resp.UserRespDTO;
import org.ginwithouta.shortlink.admin.service.GroupService;
import org.ginwithouta.shortlink.admin.service.UserService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.ginwithouta.shortlink.admin.common.constant.RedisCacheConstant.REDIS_LOCK_USER_REGISTER_KEY;
import static org.ginwithouta.shortlink.admin.common.constant.RedisCacheConstant.REDIS_USER_ALREADY_LOGIN_IN_KEY;
import static org.ginwithouta.shortlink.admin.common.enums.UserErrorCodeEnums.*;

/**
 * 用户接口实现层
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    private final GroupService groupService;

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

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        // 判断用户名是否重复
        if (hasUsername(requestParam.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        // 防止缓存穿透，多个恶意线程同时请求同一个不存在的用户名，多个线程一起查数据库导致数据库崩溃，因此加锁
        RLock lock = redissonClient.getLock(StrUtil.format(REDIS_LOCK_USER_REGISTER_KEY, requestParam.getUsername()));
        try {
            if (lock.tryLock()) {
                try {
                    int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                    if (inserted < 1) {
                        throw new ClientException(USER_SAVE_ERROR);
                    }
                } catch (DuplicateKeyException ex) {
                    throw new ClientException(USER_EXIST);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                groupService.saveGroup(requestParam.getUsername(), "默认分组");
                return ;
            }
            throw new ClientException(USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        //TODO: 验证当前用户名是否为登录用户，需要依赖网关
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class).eq(UserDO::getUsername, requestParam.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParam, UserDO.class), updateWrapper);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> wrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword());
        UserDO userDO = baseMapper.selectOne(wrapper);
        if (userDO == null) {
            throw new ClientException(USER_LOGIN_ERROR);
        }
        String key = StrUtil.format(REDIS_USER_ALREADY_LOGIN_IN_KEY, requestParam.getUsername());
        Map<Object, Object> isAlreadyLoginMap = stringRedisTemplate.opsForHash().entries(key);
        if (CollUtil.isNotEmpty(isAlreadyLoginMap)) {
            stringRedisTemplate.expire(key, 30L, TimeUnit.MINUTES);
            String token = isAlreadyLoginMap.keySet().stream()
                    .findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new ClientException(USER_LOGIN_ERROR));
            return new UserLoginRespDTO(token);
        }
        String uuid = UUID.randomUUID().toString();
        // 用户有关的 Token 存放在 Redis 中，每次验证从 Redis 中进行获取
        stringRedisTemplate.opsForHash().put(key, uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire(key, 30L, TimeUnit.DAYS);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        String key = StrUtil.format(REDIS_USER_ALREADY_LOGIN_IN_KEY, username);
        return stringRedisTemplate.opsForHash().get(key, token) != null;
    }

    @Override
    public void logout(String username, String token) {
        if (!checkLogin(username, token)) {
            throw new ClientException(USER_NOT_LOGIN);
        }
        String key = StrUtil.format(REDIS_USER_ALREADY_LOGIN_IN_KEY, username);
        stringRedisTemplate.delete(key);
    }
}
