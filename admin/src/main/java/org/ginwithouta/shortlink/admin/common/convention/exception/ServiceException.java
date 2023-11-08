package org.ginwithouta.shortlink.admin.common.convention.exception;

import org.ginwithouta.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import org.ginwithouta.shortlink.admin.common.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.convention.exception
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc :
 */
public class ServiceException extends AbstractException{
    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
