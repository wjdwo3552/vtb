package com.tvb.api.domain.user.service.impl;

import com.tvb.api.domain.user.dto.LoginRequest;
import com.tvb.api.domain.user.dto.TokenResponse;
import com.tvb.api.domain.user.entity.member.User;
import com.tvb.api.domain.user.exception.IllegalLoginTypeArgumentException;
import com.tvb.api.domain.user.exception.InvalidCredentialsException;
import com.tvb.api.domain.user.repository.UserRepository;
import com.tvb.api.domain.user.repository.auth.PasswordRepository;
import com.tvb.api.domain.user.service.AuthService;
import com.tvb.api.jwt.security.util.JWTUtil;
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
    public TokenResponse makeTokenAndLogin(LoginRequest loginRequest) {
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
            return new TokenResponse(accessToken,refreshToken,dataMap.get("userId") );
        }
        throw new InvalidCredentialsException();
    }
}