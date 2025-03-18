package com.tvb.api.jwt.controller;

import com.tvb.api.jwt.security.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/token")
public class TokenController {
    private final JWTUtil jwtUtil;

    @PostMapping("/make")
    public ResponseEntity<Map<String, String>> makeToken() {
        String accessToken = jwtUtil.createToken(Map.of("1", "2"), 10);
        String refreshToken = jwtUtil.createToken(Map.of("3", "4"), 10);

        return ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(
            @RequestHeader("Authorization") String accessTokenStr,
            @RequestParam("refreshToken") String refreshToken,
            @RequestParam("uid") String uid
    ) {
        if (accessTokenStr == null || accessTokenStr.startsWith("Bearer ")) {
            return handleException("No Access Token", 400);
        }
        if (refreshToken == null) {
            return handleException("No Refresh Token", 400);
        }
        if (uid == null) {
            return handleException("No User ID", 400);
        }
        String accessToken = accessTokenStr.substring(7);
        try {
            jwtUtil.validateToken(accessToken);
            Map<String, String> data = makeData(uid, accessToken, refreshToken);
        } catch (io.jsonwebtoken.ExpiredJwtException expiredJwtException) {
            makeNewToken(uid, refreshToken);
        } catch (Exception e) {
            return handleException(e.getMessage(), 400);
        }
        return null;
    }
    private ResponseEntity<Map<String, String>> handleException(String msg, int Status) {
        return ResponseEntity.status(Status).body(Map.of("error", msg));
    }
    private Map<String, String> makeData(String uid, String accessToken, String refreshToken) {
        return Map.of("uid", uid, "accessToken", accessToken, "refreshToken", refreshToken);
    }
    private Map<String, String> makeNewToken(String uid, String refreshToken) {
        try {
            Map<String, Object> claims = jwtUtil.validateToken(refreshToken);
            if (!uid.equals(claims.get("uid").toString())) {
                throw new RuntimeException("Invalid Refresh Token Host");
            }
            //TODO : uid를 이용해서 사용자 정보를 한번 더 확인 후 새로운 토큰 생성해야함
            String newAccessToken = jwtUtil.createToken(Map.of("uid", uid), 10);
            String newRefreshToken = jwtUtil.createToken(Map.of("uid", uid), 60);
            return makeData(uid, newAccessToken, newRefreshToken);

        } catch (Exception e) {
            handleException(e.getMessage(), 400);
        }
        return null;
    }
}