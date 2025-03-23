package com.tvb.api.domain.user.controller;

import com.tvb.api.domain.user.dto.LoginRequest;
import com.tvb.api.domain.user.dto.TokenResponse;
import com.tvb.api.domain.user.service.AuthService;
import com.tvb.api.domain.user.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/")
    public ResponseEntity<TokenResponse> makeToken(@RequestBody LoginRequest loginRequest) {
        if (authService.makeTokenAndLogin(loginRequest) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(authService.makeTokenAndLogin(loginRequest));
    }
}
