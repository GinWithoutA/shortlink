package org.ginwithouta.shortlink.project.common.enums;

import org.ginwithouta.shortlink.project.common.convention.errorcode.IErrorCode;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.enums
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接错误码枚举类
 */
public enum ShortLinkErrorCodeEnums implements IErrorCode {

    SHORT_LINK_OPERATION_FAIL("B000300", "【短链接】操作失败"),
    SHORT_LINK_CREATE_FAIL("B000301", "【短链接】创建短链接失败"),
    SHORT_LINK_CREATE_SUFFIX_OVER_FLOW("B000302", "【短链接】创建短链接后缀失败：重复尝试次数太多"),
    SHORT_LINK_BLOOM_FAIL("B000303", "【短链接】创建短链接失败：布隆过滤器误判，导致发生重复添加错误");
    private final String code;

    private final String message;

    ShortLinkErrorCodeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
