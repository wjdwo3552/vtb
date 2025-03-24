package com.tvb.api.domain.user.service.impl;

import com.tvb.api.domain.user.dto.register.RegisterRequestData;
import com.tvb.api.domain.user.dto.RegisterResponse;
import com.tvb.api.domain.user.dto.register.module.RegisterPasswordRequestData;
import com.tvb.api.domain.user.dto.register.module.RegisterProfileRequestData;
import com.tvb.api.domain.user.dto.register.module.RegisterUserRequestData;
import com.tvb.api.domain.user.entity.auth.Password;
import com.tvb.api.domain.user.entity.member.Profile;
import com.tvb.api.domain.user.entity.member.User;
import com.tvb.api.domain.user.repository.ProfileRepository;
import com.tvb.api.domain.user.repository.UserRepository;
import com.tvb.api.domain.user.repository.auth.PasswordRepository;
import com.tvb.api.domain.user.service.RegisterSerivce;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterServiceImpl implements RegisterSerivce {
    @Autowired private UserRepository userRepository;
    @Autowired private ProfileRepository profileRepository;
    @Autowired private PasswordRepository passwordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisterResponse toRegisterUser(RegisterRequestData registerRequestData) {
        RegisterUserRequestData userD_ = registerRequestData.getUser();
        RegisterProfileRequestData profileD_ = registerRequestData.getProfile();
        RegisterPasswordRequestData passwordD_ = registerRequestData.getPassword();

        User user = User.builder()
                .userId(userD_.getUserId())
                .loginType(userD_.getLoginType())
                .build();
        userRepository.save(user);

        Profile profile = Profile.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .nickname(profileD_.getNickname())
                .user(user)
                .build();
       profileRepository.save(profile);

        Password password = Password.builder()
                .password(passwordEncoder.encode(passwordD_.getPassword()))
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();
        passwordRepository.save(password);

        return toRegisterResponse(user);
    }
    //This method is deprecated.
    private User toUserEntity(RegisterUserRequestData d) {
        return User.builder()
                .userId(d.getUserId())
                .loginType(d.getLoginType())
                .build();
    }

    private RegisterResponse toRegisterResponse(User user) {
        return new RegisterResponse(user.getUserId());
    }
}
