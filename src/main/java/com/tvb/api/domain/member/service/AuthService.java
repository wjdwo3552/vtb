package com.tvb.api.domain.member.service;

import com.tvb.api.domain.member.dto.AuthRequest;
import jakarta.servlet.http.Cookie;

import java.util.Map;

public interface AuthService {
    Map<String, String> makeTokenAndLogin(AuthRequest authRequest);
    Map<String, String> RefreshToken(String accessToken, String refreshToken);

    Cookie storeRefreshTokenInCookie(String cValue);
}
