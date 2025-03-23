package com.tvb.api.domain.user.exception.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageMap> handleMethodArgumentNotValidException() {
        return ResponseEntity.status(ErrorCode.REGISTRATION_FAILURE.getHttpStatus())
                .body(new ErrorMessageMap(ErrorCode.REGISTRATION_FAILURE.getCode(), ErrorCode.REGISTRATION_FAILURE.getMessage()));    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "서버 오류가 발생했습니다.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
