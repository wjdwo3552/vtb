package com.tvb.api.domain.member.exception;

import com.tvb.api.domain.member.exception.common.AuthException;
import com.tvb.api.domain.member.exception.common.ErrorCode;

public class InvalidCredentialsException extends AuthException {
    public InvalidCredentialsException() {
        super(ErrorCode.INVALID_CREDENTIALS);
    }
}
