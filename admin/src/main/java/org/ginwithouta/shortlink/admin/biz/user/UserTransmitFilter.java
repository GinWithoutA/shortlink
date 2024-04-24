package org.ginwithouta.shortlink.admin.biz.user;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @package : org.ginwithouta.shortlink.admin.biz.user
 * @author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc :
 */
@Slf4j
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String username = httpServletRequest.getHeader("username");
        if (StrUtil.isNotBlank(username)) {
            String userId = httpServletRequest.getHeader("userId");
            String realName = httpServletRequest.getHeader("realName");
            UserInfoDTO userInfoDTO = new UserInfoDTO(userId, username, realName);
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
