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
     * 短链接延迟队列消费统计 Key
     */
    public static final String DELAY_QUEUE_STATS_KEY = "short-link:delay-queue:stats";

    /**
     * 短链接修改分组 ID 锁前缀 Key
     */
    public static final String LOCK_GID_UPDATE_KEY = "short-link:lock:update-gid:%s";

    /**
     * 消息队列中短链接监控消费者
     */
    public static final String CONSUMER_SHORT_LINK_STATS = "stream_consumer_short-link_stats_";

}
