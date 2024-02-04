package org.ginwithouta.shortlink.project.common.constant;

/**
 * @author Ginwithouta
 * Generate at 2024/2/4
 * 短链接数据统计常量类
 */
public class ShortLinkStatsConstant {

    /**
     * 短链接基础信息统计中 UV 用户标识前缀（用于判断当前用户是否访问过）
     */
    public static final String REDIS_PREFIX_LINK_STATS_UV = "short-link:stats:uv:";

    /**
     * 短链接基础信息统计中 UIP 用户标识前缀（用于判断当前 IP 是否访问过）
     */
    public static final String REDIS_PREFIX_LINK_STATS_UIP = "short-link:stats:uip:";
}
