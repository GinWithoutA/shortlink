package org.ginwithouta.shortlink.project.common.constant;

/**
 * @Package : org.ginwithouta.shortlink.project.common.constant
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc : Redis Key 常量类
 */
public class RedisKeyConstant {
    /**
     * 短链接跳转前缀Key
     */
    public static final String GOTO_SHORT_LINK_KEY = "short-link:goto:%s";

    /**
     * 短链接跳转分布式锁前缀Key
     */
    public static final String LOCK_GOTO_SHORT_LINK_KEY = "short-link:goto:goto:%s";

    /**
     * 短链接跳转是否为空 Key
     */
    public static final String GOTO_IS_NULL_SHORT_LINK_KEY = "short-link:goto:is-null:%s";

    /**
     * 短链接监控消息保存队列 Topic 缓存标识
     */
    public static final String SHORT_LINK_STATS_STREAM_TOPIC_KEY = "short-link:stats-stream";
}
