package org.ginwithouta.shortlink.admin.biz.user;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.List;

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

    // TODO: 后续还要进行更新
    private static final List<String> IGNORE_URIS = Lists.newArrayList(
            "/api/short/link/admin/v1/user/login"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        // TODO: 将登录接口过滤掉
        if (!IGNORE_URIS.contains(requestURI)) {
            String username = httpServletRequest.getHeader("username");
            String token = httpServletRequest.getHeader("token");
            Object userInfoJsonStr = stringRedisTemplate.opsForHash().get(CACHE_TOKEN_PREFIX + username, token);
            if (userInfoJsonStr != null) {
                UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                UserContext.setUser(userInfoDTO);
            }
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
