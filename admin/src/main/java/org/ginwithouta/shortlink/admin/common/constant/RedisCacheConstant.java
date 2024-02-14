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
    public static final String LOCK_USER_REGISTER_KEY = "short_link:lock_user_register";

    /**
     * 分组创建分布式锁
     */
    public static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group-create:%s";
}
