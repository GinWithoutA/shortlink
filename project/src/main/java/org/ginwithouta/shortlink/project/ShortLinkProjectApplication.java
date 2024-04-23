package org.ginwithouta.shortlink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package : org.ginwithouta.shortlink.project
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc :
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("org.ginwithouta.shortlink.project.dao.mapper")
public class ShortLinkProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkProjectApplication.class, args);
    }
}
