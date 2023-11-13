package org.ginwithouta.shortlink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package : org.ginwithouta.shortlink.project
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc :
 */
@SpringBootApplication
@MapperScan("org.ginwithouta.shortlink.project.dao.mapper")
public class ShortLinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkApplication.class, args);
    }
}
