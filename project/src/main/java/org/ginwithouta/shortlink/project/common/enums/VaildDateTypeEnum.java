package org.ginwithouta.shortlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.enums
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 有效期类型
 */
@Getter
@RequiredArgsConstructor
public enum VaildDateTypeEnum {
    /**
     * 永久有效
     */
    PERMANENT(0),
    /**
     * 自定义
     */
    CUSTOM(1);

    private final int type;
}
