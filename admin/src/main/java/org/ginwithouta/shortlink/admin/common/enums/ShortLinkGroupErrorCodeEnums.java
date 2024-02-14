package org.ginwithouta.shortlink.admin.common.enums;

import org.ginwithouta.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.enums
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc :
 */
public enum ShortLinkGroupErrorCodeEnums implements IErrorCode {

    GROUP_OPERATION_FAIL("B000300", "【短链接后管分组】操作失败"),
    GROUP_DELETE_FAIL("B000304", "【短链接后管分组】删除失败：当前对象无法删除或您没有权限"),
    GROUP_CREATE_EXCEED_MAX("B000305", "【短链接后管分组】创建失败：已达到最大分组数量");
    private final String code;

    private final String message;

    ShortLinkGroupErrorCodeEnums(String code, String message) {
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
