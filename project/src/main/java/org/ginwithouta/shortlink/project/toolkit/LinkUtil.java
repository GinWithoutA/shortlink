package org.ginwithouta.shortlink.project.toolkit;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.ginwithouta.shortlink.project.common.constant.ShortLinkConstant.DEFAULT_CACHE_VALID_TIME;

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
}
