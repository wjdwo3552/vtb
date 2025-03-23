package com.tvb.api.domain.user.exception;

import com.tvb.api.domain.user.exception.common.AuthException;
import com.tvb.api.domain.user.exception.common.ErrorCode;

public class InvalidCredentialsException extends AuthException {
    public InvalidCredentialsException() {
        super(ErrorCode.INVALID_CREDENTIALS);
    }
}
