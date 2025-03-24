package com.tvb.api.domain.user.exception;

import com.tvb.api.domain.user.exception.common.ErrorCode;

public class IllegalLoginTypeArgumentException extends RuntimeException {
    public IllegalLoginTypeArgumentException() {
        super(ErrorCode.REGISTRATION_FAILURE.getMessage());
    }
}
