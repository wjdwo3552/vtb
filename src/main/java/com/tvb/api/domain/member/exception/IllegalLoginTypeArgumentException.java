package com.tvb.api.domain.member.exception;

import com.tvb.api.domain.member.exception.common.ErrorCode;

public class IllegalLoginTypeArgumentException extends RuntimeException {
    public IllegalLoginTypeArgumentException() {
        super(ErrorCode.REGISTRATION_FAILURE.getMessage());
    }
}
