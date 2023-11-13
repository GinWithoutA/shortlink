package org.ginwithouta.shortlink.project.common.convention.exception;

import org.ginwithouta.shortlink.project.common.convention.errorcode.BaseErrorCode;
import org.ginwithouta.shortlink.project.common.convention.errorcode.IErrorCode;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.convention.exception
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc :
 */
public class RemoteException extends AbstractException {

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
