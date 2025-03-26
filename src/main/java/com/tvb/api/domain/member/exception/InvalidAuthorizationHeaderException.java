package com.tvb.api.domain.member.exception;

import com.tvb.api.domain.member.exception.common.AuthException;
import com.tvb.api.domain.member.exception.common.ErrorCode;

public class InvalidAuthorizationHeaderException extends AuthException {
    public InvalidAuthorizationHeaderException() {

        super(ErrorCode.INVALID_AUTHORIZATION_HEADER);
    }
}
