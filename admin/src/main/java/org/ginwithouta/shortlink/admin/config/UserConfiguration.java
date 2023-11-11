package org.ginwithouta.shortlink.admin.config;

import org.ginwithouta.shortlink.admin.biz.user.UserTransmitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Package : org.ginwithouta.shortlink.admin.config
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc :
 */
@Configuration
public class UserConfiguration {
    /**
     * 用户信息传递过滤器
     */
    @Bean
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransmitFilter(StringRedisTemplate stringRedisTemplate) {
        FilterRegistrationBean<UserTransmitFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserTransmitFilter(stringRedisTemplate));
        registration.addUrlPatterns("/*");
        // 过滤器执行顺序，数值越小，执行顺序越高
        registration.setOrder(0);
        return registration;
    }
}
