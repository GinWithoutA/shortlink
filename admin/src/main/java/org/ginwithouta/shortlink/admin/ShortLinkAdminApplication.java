package org.ginwithouta.shortlink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Package : PACKAGE_NAME
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc :
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("org.ginwithouta.shortlink.admin.remote")
@MapperScan("org.ginwithouta.shortlink.admin.dao.mapper")
public class ShortLinkAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkAdminApplication.class, args);
    }
}
