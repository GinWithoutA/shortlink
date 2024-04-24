package org.ginwithouta.shortlink.aggregation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Ginwithouta
 * Generate at 2024/4/24
 * 短链接聚合服务启动类
 */
@SpringBootApplication(scanBasePackages = {
        "org.ginwithouta.shortlink.project",
        "org.ginwithouta.shortlink.admin",
        "org.ginwithouta.shortlink.aggregation"
})
@EnableDiscoveryClient
@MapperScan(value = {
        "org.ginwithouta.shortlink.project.dao.mapper",
        "org.ginwithouta.shortlink.admin.dao.mapper"
})
public class ShortLinkAggregationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkAggregationApplication.class, args);
    }
}