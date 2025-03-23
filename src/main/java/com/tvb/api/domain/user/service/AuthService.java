package com.tvb.api.domain.user.service;

import com.tvb.api.domain.user.dto.LoginRequest;
import com.tvb.api.domain.user.dto.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    TokenResponse makeTokenAndLogin(LoginRequest loginRequest);
}
