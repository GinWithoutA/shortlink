package org.ginwithouta.shortlink.project.mq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.REDIS_STREAM_SHORT_LINK_STATS_TOPIC_KEY;

/**
 * @author Ginwithouta
 * Generate at 2024/2/14
 * 短链接监控状态保存消息队列生产者
 */
@Component
@RequiredArgsConstructor
public class ShortLinkStatsSaveProducer {
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 发送消费短链接统计
     */
    public void send(Map<String, String> producerMap) {
        stringRedisTemplate.opsForStream().add(REDIS_STREAM_SHORT_LINK_STATS_TOPIC_KEY, producerMap);
    }
}
