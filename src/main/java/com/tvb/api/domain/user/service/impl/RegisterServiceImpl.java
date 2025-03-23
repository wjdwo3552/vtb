package com.tvb.api.domain.user.service.impl;

import com.tvb.api.domain.user.dto.RegisterRequest;
import com.tvb.api.domain.user.dto.RegisterResponse;
import com.tvb.api.domain.user.entity.User;
import com.tvb.api.domain.user.repository.UserRepository;
import com.tvb.api.domain.user.service.RegisterSerivce;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterSerivce {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse toRegisterUser(RegisterRequest registerRequest) {
        User user = toUserEntity(registerRequest);
        userRepository.save(user);
        return toRegisterResponse(user);
    }

    private User toUserEntity(RegisterRequest registerRequest) {
        return User.builder()
                .phone(registerRequest.getPhone())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .name(registerRequest.getName())
                .grade(registerRequest.getGrade())
                .provider(registerRequest.getProvider())
                .profileImage(registerRequest.getProfileImage())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    private RegisterResponse toRegisterResponse(User user) {
        return new RegisterResponse(user.getEmail());
    }
}
