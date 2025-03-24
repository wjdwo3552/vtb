package com.tvb.api.domain.member.service;

import com.tvb.api.domain.member.dto.LoginRequest;
import com.tvb.api.domain.member.dto.TokenResponse;

public interface AuthService {
    TokenResponse makeTokenAndLogin(LoginRequest loginRequest);
}
