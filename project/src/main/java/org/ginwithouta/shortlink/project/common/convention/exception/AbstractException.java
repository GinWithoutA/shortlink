package org.ginwithouta.shortlink.project.common.convention.exception;

import lombok.Data;
import org.ginwithouta.shortlink.project.common.convention.errorcode.IErrorCode;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.convention.exception
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc : 抽象项目中三类异常实体，客户端异常、服务端异常以及远程服务调用异常
 * @see ClientException
 * @see ServiceException
 * @see RemoteException
 */
@Data
public abstract class AbstractException extends RuntimeException {
    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null).orElse(errorCode.message());
    }
}
