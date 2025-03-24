package com.tvb.api.domain.member.controller;

import com.tvb.api.domain.member.dto.LoginRequest;
import com.tvb.api.domain.member.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/")
    public ResponseEntity<?> login(HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
        return Optional.ofNullable(authService.makeTokenAndLogin(loginRequest))
                .map(tokenResponse -> Pair.of(
                        authService.storeRefreshTokenInCookie(tokenResponse.get("refreshToken")),
                        tokenResponse.get("accessToken")
                ))
                .map(pair -> {
                    response.addCookie(pair.getFirst());
                    return ResponseEntity.ok(Map.of("accessToken", pair.getSecond()));
                })
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));

    }
}
