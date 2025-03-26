package com.tvb.api.domain.member.exception;

import com.tvb.api.domain.member.exception.common.AuthException;
import com.tvb.api.domain.member.exception.common.ErrorCode;

public class TokenNotFoundException extends AuthException {
    public TokenNotFoundException() {
        super(ErrorCode.TOKEN_NOT_FOUND);
    }
}
