package com.tvb.api.domain.member.service;

import com.tvb.api.domain.member.dto.LoginRequest;
import jakarta.servlet.http.Cookie;

import java.util.Map;

public interface AuthService {
    Map<String, String> makeTokenAndLogin(LoginRequest loginRequest);
    Cookie storeRefreshTokenInCookie(String cValue);
}
