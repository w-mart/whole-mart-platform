package com.wholemart.common.exception;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String messageKey, Object... arguments) {
        super(messageKey, arguments);
    }
}
