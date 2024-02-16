package org.ginwithouta.shortlink.admin.common.constant;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.constant
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周五
 * @Desc : 短链接后管Rediss缓存常量类
 */
public class RedisCacheConstant {

    /**
     * 用户注册分布式锁
     */
    public static final String REDIS_LOCK_USER_REGISTER_KEY = "short-link:lock:user:register:%s";

    /**
     * 分组创建分布式锁
     */
    public static final String REDIS_LOCK_GROUP_CREATE_KEY = "short-link:lock:group:create:%s-%s";

    /**
     * 判断用户名是否登陆 KEY
     */
    public static final String REDIS_USER_ALREADY_LOGIN_IN_KEY = "short-link:login:%s";
}
