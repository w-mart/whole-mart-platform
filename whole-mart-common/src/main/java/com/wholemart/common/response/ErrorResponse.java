package com.wholemart.common.response;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(int status, String code, String message, Map<String, String> details, Instant timestamp) {
    public ErrorResponse(int status, String code, String message) {
        this(status, code, message, Map.of(), Instant.now());
    }
}
