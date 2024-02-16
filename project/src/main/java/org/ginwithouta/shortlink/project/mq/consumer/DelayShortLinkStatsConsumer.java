package org.ginwithouta.shortlink.project.mq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.project.common.convention.exception.ServiceException;
import org.ginwithouta.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import org.ginwithouta.shortlink.project.mq.idempotent.MessageQueueIdempotentHandler;
import org.ginwithouta.shortlink.project.service.ShortLinkService;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

import static org.ginwithouta.shortlink.project.common.constant.RedisKeyConstant.DELAY_QUEUE_STATS_KEY;
import static org.ginwithouta.shortlink.project.common.enums.ShortLinkErrorCodeEnums.SHORT_LINK_STATS_MQ_NOT_ACCOMPLISH;

/**
 * @author Ginwithouta
 * Generate at 2024/2/14
 * 短链接统计记录延迟消费消费者
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DelayShortLinkStatsConsumer implements InitializingBean {
    private final RedissonClient redissonClient;
    private final ShortLinkService shortLinkService;
    private final MessageQueueIdempotentHandler messageQueueIdempotentHandler;

    public void onMessage() {
        Executors.newSingleThreadExecutor(
                runnable -> {
                    Thread thread = new Thread(runnable);
                    thread.setName("delay_short-link_stats_consumer");
                    thread.setDaemon(Boolean.TRUE);
                    return thread;
                }
        ).execute(() -> {
            RBlockingDeque<ShortLinkStatsRecordDTO> blockingDeque = redissonClient.getBlockingDeque(DELAY_QUEUE_STATS_KEY);
            RDelayedQueue<ShortLinkStatsRecordDTO> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
            while (Boolean.TRUE) {
                try {
                    ShortLinkStatsRecordDTO statsRecord = delayedQueue.poll();
                    if (statsRecord != null) {
                        if (!messageQueueIdempotentHandler.isMessageProcessed(statsRecord.getKeys())) {
                            if (messageQueueIdempotentHandler.isAccomplish(statsRecord.getKeys())) {
                                return;
                            }
                            throw new ServiceException(SHORT_LINK_STATS_MQ_NOT_ACCOMPLISH);
                        }
                        try {
                            shortLinkService.shortLinkStats(null, null, statsRecord);
                        } catch (Throwable ex) {
                            messageQueueIdempotentHandler.delMessageProcessed(statsRecord.getKeys());
                            log.error("延迟记录短链接监控消费异常", ex);
                        }
                        messageQueueIdempotentHandler.setAccomplish(statsRecord.getKeys());
                        break;
                    }
                    LockSupport.parkUntil(500);
                } catch (Throwable ignored) {

                }
            }
        });
    }

    @Override
    public void afterPropertiesSet() {
        onMessage();
    }
}
