package com.wholemart.common.exception;

public class BusinessException extends RuntimeException {
    private final Object[] arguments;

    public BusinessException(String messageKey, Object... arguments) {
        super(messageKey);
        this.arguments = arguments.clone();
    }

    public Object[] getArguments() {
        return arguments.clone();
    }
}
