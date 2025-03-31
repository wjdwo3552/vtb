package com.tvb.api.domain.member.oauth2;

import com.tvb.api.domain.member.entity.Password;
import com.tvb.api.domain.member.entity.Profile;
import com.tvb.api.domain.member.entity.SocialLogin;
import com.tvb.api.domain.member.entity.User;
import com.tvb.api.domain.member.entity.logintype.LoginType;
import com.tvb.api.domain.member.repository.PasswordRepository;
import com.tvb.api.domain.member.repository.ProfileRepository;
import com.tvb.api.domain.member.repository.SocialLoginRepository;
import com.tvb.api.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final SocialLoginRepository socialLoginRepository;
    private final PasswordRepository passwordRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        //TODO: 개발자 구현 로직

        String providerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        SocialLogin socialLogin = socialLoginRepository.findBySocialId(email)
                .orElseGet(() -> registerSocial(providerId, email, name));
        return oAuth2User;
    }

    private SocialLogin registerSocial(String providerId, String email, String name) {
        User user = User.builder()
                .userId(java.util.UUID.randomUUID().toString())
                .loginType(LoginType.GOOGLE)
                .build();
        userRepository.save(user);
        log.info("{}", user);

        Profile profile = Profile.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .nickname(name)
                .user(user)
                .build();
        profileRepository.save(profile);
        log.info("{}", profile);

        Password password = Password.builder()
                .password(java.util.UUID.randomUUID().toString())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();
        passwordRepository.save(password);
        log.info("{}", password);

        return socialLoginRepository.save(SocialLogin.builder()
                .providerId(providerId)
                .socialId(email)
                .user(user)
                .build()
        );
    }
}
