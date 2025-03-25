package com.tvb.api.domain.member.exception;

import com.tvb.api.domain.member.exception.common.AuthException;
import com.tvb.api.domain.member.exception.common.ErrorCode;

public class IllegalLoginTypeArgumentException extends AuthException {
    public IllegalLoginTypeArgumentException() {
        super(ErrorCode.REGISTRATION_FAILURE);
    }
}
