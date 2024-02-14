package org.ginwithouta.shortlink.project.mq.idempotent;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Ginwithouta
 * Generate at 2024/2/14
 * 消息队列幂等处理器
 */
@Component
@RequiredArgsConstructor
public class MessageQueueIdempotentHandler {
    private final StringRedisTemplate stringRedisTemplate;
    private static final String IDEMPOTENT_KEY_PREFIX = "short-link:idempotent:";

    /**
     * 判断消息是否已经消费过
     */
    public boolean isMessageProcessed(String messageId) {
        String key = IDEMPOTENT_KEY_PREFIX + messageId;
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(key, "0", 2, TimeUnit.MINUTES));
    }

    public boolean isAccomplish(String messageId) {
        String key = IDEMPOTENT_KEY_PREFIX + messageId;
        return Objects.equals(stringRedisTemplate.opsForValue().get(key), "1");
    }

    /**
     * 设置消息流程执行完成标识
     */
    public void setAccomplish(String messageId) {
        String key = IDEMPOTENT_KEY_PREFIX + messageId;
        stringRedisTemplate.opsForValue().set(key, "1", 2, TimeUnit.MINUTES);
    }

    /**
     * 如果消息处理出现异常情况，删除幂等标识，等待下一次消费
     */
    public void delMessageProcessed(String messageId) {
        String key = IDEMPOTENT_KEY_PREFIX + messageId;
        stringRedisTemplate.delete(key);
    }

}
