package org.ginwithouta.shortlink.project.config;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.mq.consumer.ShortLinkStatsSaveConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.REDIS_STREAM_SHORT_LINK_STATS_CONSUMER_KEY;

/**
 * @author Ginwithouta
 * Generate at 2024/2/16
 * Redis Stream 消息队列配置
 */
@Configuration
@RequiredArgsConstructor
public class RedisStreamConfig {
    private final RedisConnectionFactory redisConnectionFactory;
    private final ShortLinkStatsSaveConsumer shortLinkStatsSaveConsumer;

    @Value("${spring.data.redis.channel-topic.short-link-stats}")
    private String topic;
    @Value("${spring.data.redis.channel-topic.short-link-stats-group}")
    private String group;

    @Bean
    public ExecutorService asyncStreamConsumer() {
        AtomicInteger index = new AtomicInteger();
        // 获取 CPU 核心数量
        int processors = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(processors,
                processors + processors >> 1,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                runnable -> {
                    Thread thread = new Thread(runnable);
                    thread.setName(StrUtil.format(REDIS_STREAM_SHORT_LINK_STATS_CONSUMER_KEY, index.incrementAndGet()));
                    thread.setDaemon(true);
                    return thread;
                });
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer(ExecutorService asyncStreamConsumer) {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                        // 一次获取多少条消息
                        .batchSize(10)
                        // 执行从 Stream 拉取到消息的任务流程
                        .executor(asyncStreamConsumer)
                        // 如果没有拉取到消息，阻塞多久，超过 ${spring.data.redis.timeout} 就会超时
                        .pollTimeout(Duration.ofSeconds(3))
                        .build();
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer =
                StreamMessageListenerContainer.create(redisConnectionFactory, options);
        streamMessageListenerContainer.receiveAutoAck(Consumer.from(group, "stats-consumer"),
                StreamOffset.create(topic, ReadOffset.lastConsumed()), shortLinkStatsSaveConsumer);
        return streamMessageListenerContainer;
    }

}
