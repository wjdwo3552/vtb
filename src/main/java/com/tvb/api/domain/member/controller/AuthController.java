package com.tvb.api.domain.member.controller;

import com.tvb.api.domain.member.dto.AuthRequest;
import com.tvb.api.domain.member.entity.User;
import com.tvb.api.domain.member.service.AuthService;
import com.tvb.api.jwt.security.util.JWTUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse response, @RequestBody AuthRequest authRequest) {
        log.info("Login request: {}", authRequest);
        return Optional.ofNullable(authService.makeTokenAndLogin(authRequest))
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
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader(value = "Authorization", required = false) String accessToken,
                                     @CookieValue(name = "refreshToken") String refreshToken,
                                     HttpServletResponse response) {
        Map<String, String> map = authService.RefreshToken(accessToken, refreshToken);
        log.info("Refresh token: {}", map.get("refreshToken"));
        log.info("Access token: {}", map.get("accessToken"));
        response.addCookie(authService.storeRefreshTokenInCookie(map.get("refreshToken")));
        return ResponseEntity.ok(Map.of("accessToken", map.get("accessToken")));
    }
}
