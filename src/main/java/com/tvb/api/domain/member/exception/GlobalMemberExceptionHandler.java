package com.tvb.api.domain.member.exception;

import com.tvb.api.domain.member.exception.common.AuthException;
import com.tvb.api.domain.member.exception.common.ErrorCode;
import com.tvb.api.domain.member.exception.common.ErrorMessageMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalMemberExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorMessageMap> handleAuthException(AuthException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(new ErrorMessageMap(e.getErrorCode().getCode(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageMap> handleMethodArgumentNotValidException() {
        return ResponseEntity.status(ErrorCode.REGISTRATION_FAILURE.getHttpStatus())
                .body(new ErrorMessageMap(ErrorCode.REGISTRATION_FAILURE.getCode(), ErrorCode.REGISTRATION_FAILURE.getMessage()));    }


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> handleException(Exception e) {
//        Map<String, String> error = new HashMap<>();
//        error.put("error", "서버 오류가 발생했습니다.");
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
