package org.ginwithouta.shortlink.admin.biz.user;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.admin.common.convention.exception.ClientException;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.ginwithouta.shortlink.admin.common.constant.RedisCacheConstant.REDIS_USER_ALREADY_LOGIN_IN_KEY;
import static org.ginwithouta.shortlink.admin.common.enums.UserErrorCodeEnums.USER_TOKEN_CHECK_FAIL;

/**
 * @package : org.ginwithouta.shortlink.admin.biz.user
 * @author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc :
 */
@Slf4j
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;

    // TODO: 后续还要进行更新
    private static final List<String> IGNORE_URIS = Lists.newArrayList(
            "/api/short/link/admin/v1/user/login",
            // 过滤注册链接
            // 下面这个链接存在很多个重名但是不同请求方法的链接，因此在下面的内容中需要判断请求链接
            "/api/short/link/admin/v1/user",
            "/api/short/link/admin/v1/user/has-username"

    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);
        if (!IGNORE_URIS.contains(requestURI)) {
            String method = httpServletRequest.getMethod();
            if (!(Objects.equals(requestURI, IGNORE_URIS.get(1)) && Objects.equals(method, "POST"))) {
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if (!StrUtil.isAllNotBlank(username, token)) {
                    // TODO 全局异常捕捉是捕捉不到Filter中的异常的，Filter的处理是在Controller之前的，因此无法捕捉到
                    throw new ClientException(USER_TOKEN_CHECK_FAIL);
                }
                Object userInfoJsonStr;
                try {
                    userInfoJsonStr = stringRedisTemplate.opsForHash().get(StrUtil.format(REDIS_USER_ALREADY_LOGIN_IN_KEY, username), token);
                    if (userInfoJsonStr == null) {
                        throw new ClientException(USER_TOKEN_CHECK_FAIL);
                    }
                } catch (Exception e) {
                    throw new ClientException(USER_TOKEN_CHECK_FAIL);
                }
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
