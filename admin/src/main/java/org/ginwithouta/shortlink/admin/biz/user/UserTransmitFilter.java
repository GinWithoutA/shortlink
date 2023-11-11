package org.ginwithouta.shortlink.admin.biz.user;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;

/**
 * @package : org.ginwithouta.shortlink.admin.biz.user
 * @author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc :
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;
    private static final String CACHE_TOKEN_PREFIX = "login-";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String username = httpServletRequest.getHeader("username");
        String token = httpServletRequest.getHeader("token");
        Object userInfoJsonStr = stringRedisTemplate.opsForHash().get(CACHE_TOKEN_PREFIX + username, token);
        if (userInfoJsonStr != null) {
            UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
            UserContext.setUser(userInfoDTO);
        }
        try {
            // 当前拦截器的工作完成，继续执行请求
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // 当前请求执行完成，回来还原现场
            UserContext.removeUser();
        }
    }
}
