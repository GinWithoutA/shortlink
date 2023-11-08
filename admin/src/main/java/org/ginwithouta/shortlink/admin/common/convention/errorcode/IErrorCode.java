package org.ginwithouta.shortlink.admin.common.convention.errorcode;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.convention.errorcode
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc : 平台错误码
 */
public interface IErrorCode {

    /**
     * 错误码
     */
    String code();

    /**
     * 错误信息
     */
    String message();
}
