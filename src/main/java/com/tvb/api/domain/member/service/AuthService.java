package com.tvb.api.domain.member.service;

import com.tvb.api.domain.member.dto.LoginRequest;
import com.tvb.api.domain.member.dto.TokenResponse;
import jakarta.servlet.http.Cookie;

public interface AuthService {
    TokenResponse makeTokenAndLogin(LoginRequest loginRequest);
    Cookie storeRefreshTokenInCookie(String cValue);
}
