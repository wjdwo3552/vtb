package com.tvb.api.domain.member.service.impl;

import com.tvb.api.domain.member.dto.LoginRequest;
import com.tvb.api.domain.member.entity.User;
import com.tvb.api.domain.member.exception.IllegalLoginTypeArgumentException;
import com.tvb.api.domain.member.exception.InvalidCredentialsException;
import com.tvb.api.domain.member.repository.UserRepository;
import com.tvb.api.domain.member.repository.PasswordRepository;
import com.tvb.api.domain.member.service.AuthService;
import com.tvb.api.jwt.security.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PasswordRepository passwordRepository;

    @Override
    public Map<String, String> makeTokenAndLogin(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUserId(loginRequest.getUser().getUserId());

        Optional<String> password = passwordRepository.findPasswordByUser(
                user.orElseThrow(IllegalLoginTypeArgumentException::new
        ));

        if (password.isPresent() &&
                passwordEncoder.matches(
                        loginRequest.getPassword().getPassword(), password.get())) {

            Map<String, String> dataMap = loginRequest.getDataMap();
            String accessToken = jwtUtil.createToken(dataMap, 10);
            String refreshToken = jwtUtil.createToken(dataMap, 10);
            return Map.of("accessToken", accessToken, "refreshToken",refreshToken);
        }
        throw new InvalidCredentialsException();
    }

    @Override
    public Cookie storeRefreshTokenInCookie(String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);//TODO: 현재는 https 통신을 지원하지 않아 비활성화 했지만 추후 https 통신 연결시 true 바꾸어야 한다.
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        return cookie;
    }
}