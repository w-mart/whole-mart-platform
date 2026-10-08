package com.wholemart.common.exception;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(String messageKey, Object... arguments) {
        super(messageKey, arguments);
    }
}
