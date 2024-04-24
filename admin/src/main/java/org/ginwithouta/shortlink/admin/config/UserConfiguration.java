package org.ginwithouta.shortlink.admin.config;

import org.ginwithouta.shortlink.admin.biz.user.UserFlowRiskControlFilter;
import org.ginwithouta.shortlink.admin.biz.user.UserTransmitFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransmitFilter() {
        FilterRegistrationBean<UserTransmitFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserTransmitFilter());
        registration.addUrlPatterns("/*");
        // 过滤器执行顺序，数值越小，执行顺序越高
        registration.setOrder(0);
        return registration;
    }

    /**
     * 用户操作流量风控过滤器
     */
    @Bean
    // 可以再这里判断是否需要执行，也可以在具体的 doFilter 中判断
    @ConditionalOnProperty(name = "short-link.flow-limit.enable", havingValue = "true")
    public FilterRegistrationBean<UserFlowRiskControlFilter> globalUserFlowRiskFilter(
            StringRedisTemplate stringRedisTemplate,
            UserFlowRiskControlConfiguration userFlowRiskControlConfiguration) {
        FilterRegistrationBean<UserFlowRiskControlFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserFlowRiskControlFilter(stringRedisTemplate, userFlowRiskControlConfiguration));
        registration.addUrlPatterns("/*");
        registration.setOrder(10);
        return registration;
    }
}