package com.tvb.api.domain.member.exception;

import com.tvb.api.domain.member.exception.common.AuthException;
import com.tvb.api.domain.member.exception.common.ErrorCode;

public class DataIntegrityViolationException extends AuthException {
    public DataIntegrityViolationException() {
        super(ErrorCode.REGISTRATION_FAILURE);
    }
}
