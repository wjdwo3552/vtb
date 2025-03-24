package com.tvb.api.domain.member.controller;

import com.tvb.api.domain.member.dto.LoginRequest;
import com.tvb.api.domain.member.dto.TokenResponse;
import com.tvb.api.domain.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        if (authService.makeTokenAndLogin(loginRequest) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(authService.makeTokenAndLogin(loginRequest));
    }
}
