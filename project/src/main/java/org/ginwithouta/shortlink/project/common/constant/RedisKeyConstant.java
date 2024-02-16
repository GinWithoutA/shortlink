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
    public static final String REDIS_GOTO_SHORT_LINK_KEY = "short-link:goto:%s";

    /**
     * 短链接跳转分布式锁前缀Key
     */
    public static final String REDIS_LOCK_GOTO_SHORT_LINK_KEY = "short-link:lock:goto:%s";

    /**
     * 短链接跳转是否为空 Key
     */
    public static final String REDIS_GOTO_SHORT_LINK_IS_NULL_KEY = "short-link:goto:is-null:%s";

    /**
     * 短链接延迟队列消费统计 Key
     */
    public static final String REDIS_DELAY_QUEUE_STATS_KEY = "short-link:delay-queue:stats:%s";

    /**
     * 短链接修改分组 ID 锁前缀 Key
     */
    public static final String REDIS_LOCK_GID_UPDATE_KEY = "short-link:lock:update-gid:%s";

    /**
     * 消息队列中短链接监控消费者
     */
    public static final String REDIS_STREAM_SHORT_LINK_STATS_CONSUMER_KEY = "short-link:stream:stat:consumer:%s";

    /**
     * 短链接基础信息统计中 UV 用户标识前缀（用于判断当前用户是否访问过）
     */
    public static final String REDIS_SHORT_LINK_STATS_UV_KEY = "short-link:stats:uv:%s";

    /**
     * 短链接基础信息统计中 UIP 用户标识前缀（用于判断当前 IP 是否访问过）
     */
    public static final String REDIS_SHORT_LINK_STATS_UIP_KEY = "short-link:stats:uip:%s";

    /**
     * 短链接监控消息保存队列 TOPIC KEY
     */
    public static final String REDIS_STREAM_SHORT_LINK_STATS_TOPIC_KEY = "short-link:stream:stats";

    /**
     * 短链接监控消息保存队列 GROUP KEY
     */
    public static final String REDIS_STREAM_SHORT_LINK_STATS_GROUP_KEY = "short_link:stream:stats:only-group";

}
