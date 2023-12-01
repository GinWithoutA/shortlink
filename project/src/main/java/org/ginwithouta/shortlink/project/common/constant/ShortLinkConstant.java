package org.ginwithouta.shortlink.project.common.constant;

/**
 * @Package : org.ginwithouta.shortlink.project.common.constant
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周四
 * @0Desc : 短链接常量类
 */
public class ShortLinkConstant {
    /**
     * 永久短链接缓存默认有效时间
     */
    public static final long DEFAULT_CACHE_VALID_TIME = 2626560000L;

    /**
     * 短链接不存在时跳转路径
     */
    public static final String REDIRECT_TO_NOT_FOUND_URI = "/page/notfound";

    /**
     * 短链接 Domain 前缀
     */
    public static final String DOMAIN_PREFIX = "http://";

    /**
     * 用户真实 IP 地址默认值
     */
    public static final String USER_REAL_IP_DEFAULT = "unknown";

    /**
     * 高德 API 访问路径：根据用户 IP 获取用户所在地区
     */
    public static final String AMAP_REMOTE_URL = "https://restapi.amap.com/v3/ip";
}
