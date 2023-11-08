package org.ginwithouta.shortlink.admin.common.enums;

import org.ginwithouta.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.enums
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc :
 */
public enum UserErrorCodeEnums implements IErrorCode {

    USER_NULL("B000200", "用户记录不存在"),

    USER_EXIST("B000201", "用户记录已存在");

    private final String code;

    private final String message;

    UserErrorCodeEnums(String code, String message) {
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
