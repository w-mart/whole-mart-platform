package com.wholemart.identity.controller;

import com.wholemart.common.exception.BusinessException;
import com.wholemart.common.exception.ResourceNotFoundException;
import com.wholemart.common.exception.UnauthorizedException;
import com.wholemart.common.constants.ErrorCodeConstants;
import com.wholemart.common.constants.MessageConstants;
import com.wholemart.common.response.ErrorResponse;
import com.wholemart.common.message.MessageResolver;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApiExceptionHandler {
    private final MessageResolver messages;

    public ApiExceptionHandler(MessageResolver messages) {
        this.messages = messages;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException exception) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ErrorCodeConstants.NOT_FOUND, messages.get(exception.getMessage(), exception.getArguments()))); }
    @ExceptionHandler(UnauthorizedException.class)
    ResponseEntity<ErrorResponse> unauthorized(UnauthorizedException exception) { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ErrorCodeConstants.UNAUTHORIZED, messages.get(exception.getMessage(), exception.getArguments()))); }
    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ErrorResponse> business(BusinessException exception) { return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ErrorCodeConstants.BUSINESS_ERROR, messages.get(exception.getMessage(), exception.getArguments()))); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage(), (a, b) -> a));
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ErrorCodeConstants.VALIDATION_ERROR, MessageConstants.VALIDATION_FAILED, errors, java.time.Instant.now()));
    }
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<ErrorResponse> responseStatus(ResponseStatusException exception) {
        int status = exception.getStatusCode().value();
        return ResponseEntity.status(status).body(new ErrorResponse(status, ErrorCodeConstants.REQUEST_ERROR, messages.get(exception.getReason() == null ? MessageConstants.GENERIC_REQUEST_FAILURE : exception.getReason())));
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> unexpected(Exception exception) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeConstants.INTERNAL_ERROR, messages.get(MessageConstants.GENERIC_INTERNAL_ERROR))); }
}
