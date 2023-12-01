package org.ginwithouta.shortlink.project.toolkit;

import cn.hutool.core.date.LocalDateTimeUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.ginwithouta.shortlink.project.common.constant.ShortLinkConstant.DEFAULT_CACHE_VALID_TIME;
import static org.ginwithouta.shortlink.project.common.constant.ShortLinkConstant.USER_REAL_IP_DEFAULT;

/**
 * @Package : org.ginwithouta.shortlink.project.toolkit
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周四
 * @Desc : 短链接工具类
 */
public class LinkUtil {

    /**
     * 获取短链接的有效时间
     * @param validDate 指定的时间
     * @return 最终的有效时间戳
     */
    public static long getLinkCacheValidDate(LocalDateTime validDate) {
        return Optional.ofNullable(validDate)
                .map(each -> LocalDateTimeUtil.between(LocalDateTime.now(), each, ChronoUnit.MILLIS))
                .orElse(DEFAULT_CACHE_VALID_TIME);
    }

    /**
     * 获取用户真实 IP
     * @param request 请求 request
     * @return 用户真实 IP
     */
    public static String getRealIp(HttpServletRequest request) {
        // 获取X-Forwarded-For头部信息，该头部通常包含了用户真实的IP地址
        String ipAddress = request.getHeader("X-Forwarded-For");

        // 如果X-Forwarded-For头部信息为空或者为unknown，尝试获取其他头部信息
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_FORWARDED");
        }
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_VIA");
        }
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("REMOTE_ADDR");
        }

        // 如果以上方法都无法获取到真实IP，则返回默认值
        if (ipAddress == null || ipAddress.isEmpty() || USER_REAL_IP_DEFAULT.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }
}
