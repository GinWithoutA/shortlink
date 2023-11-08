package org.ginwithouta.shortlink.admin.common.convention.exception;

import org.ginwithouta.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import org.ginwithouta.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.convention.exception
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc :
 */
public class ClientException extends AbstractException{
    public ClientException(IErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }

    public ClientException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + '\'' +
                ", message='" + errorMessage + '\'' +
                '}';
    }
}
