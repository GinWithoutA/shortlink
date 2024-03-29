package org.ginwithouta.shortlink.project.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package : org.ginwithouta.shortlink.project.config
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc :
 */
@Configuration
public class RBloomFilterConfig {

    /**
     * 防止短链接创建查询数据库的布隆过滤器
     * @param redissonClient
     * @return
     */
    @Bean
    public RBloomFilter<String> shortLinkUriCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter("shortLinkUriCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(100000000L, 0.001);
        return cachePenetrationBloomFilter;
    }
}
