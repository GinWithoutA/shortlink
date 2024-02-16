package org.ginwithouta.shortlink.project.initialize;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.REDIS_STREAM_SHORT_LINK_STATS_GROUP_KEY;
import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.REDIS_STREAM_SHORT_LINK_STATS_TOPIC_KEY;

/**
 * @author Ginwithouta
 * Generate at 2024/2/16
 * 初始化短链接监控消息队列消费者组
 */
@Component
@RequiredArgsConstructor
public class ShortLinkStatsStreamInitializeTask implements InitializingBean {
    private final StringRedisTemplate stringRedisTemplate;
    @Override
    public void afterPropertiesSet() {
        Boolean hasKey = stringRedisTemplate.hasKey(REDIS_STREAM_SHORT_LINK_STATS_TOPIC_KEY);
        if (hasKey == null || !hasKey) {
            stringRedisTemplate.opsForStream().createGroup(REDIS_STREAM_SHORT_LINK_STATS_TOPIC_KEY, REDIS_STREAM_SHORT_LINK_STATS_GROUP_KEY);
        }
    }
}
