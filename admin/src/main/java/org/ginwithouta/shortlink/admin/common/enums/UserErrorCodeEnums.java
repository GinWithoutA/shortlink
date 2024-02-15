package org.ginwithouta.shortlink.admin.common.enums;

import org.ginwithouta.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.enums
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc :
 */
public enum UserErrorCodeEnums implements IErrorCode {

    USER_TOKEN_CHECK_FAIL("A000200", "用户TOKEN验证失败"),
    USER_NULL("B000200", "用户记录不存在"),
    USER_NAME_EXIST("B000201", "用户名已存在"),
    USER_EXIST("B000202", "用户已存在"),
    USER_SAVE_ERROR("B000203", "用户记录新增失败"),
    USER_LOGIN_ERROR("B000204", "用户名或密码错误"),
    USER_ALREADY_LOGIN("B000205", "用户已登录"),
    USER_NOT_LOGIN("B000206", "用户未登录"),
    USER_INFO_ERROR("A000210", "用户信息存在异常"),
    USER_INFO_GROUP_EMPTY("A000211", "当前用户没有分组信息"),
    USER_FLOW_LIMIT_ERROR("B000221", "当前用户已被风控");

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
