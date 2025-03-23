package com.tvb.api.domain.user.exception.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorMessageMap> handleAuthException(AuthException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(new ErrorMessageMap(e.getErrorCode().getCode(), e.getMessage()));
    }
}
