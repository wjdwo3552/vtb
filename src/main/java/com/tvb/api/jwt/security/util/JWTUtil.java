package com.tvb.api.jwt.security.util;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
    private final String key;

    public JWTUtil(@Value("${jwt.secret.key}") String key) {
        this.key = key;
    }

    private SecretKey getSecretKey() {

        try {
            return Keys.hmacShaKeyFor(key.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Error while generating JWT secret key", e);
        }
    }

    public String createToken(Map<String, String> valueMap, int min) {
        SecretKey key = getSecretKey();

        return Jwts.builder().header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .expiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .claims(valueMap)
                .signWith(key)
                .compact();
    }

    public Map<String, Object> validateToken(String token) {
        SecretKey key = null;
        try {
            key = getSecretKey();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return Jwts.parser().verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
