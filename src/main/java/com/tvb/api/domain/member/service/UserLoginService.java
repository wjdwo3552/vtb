package com.tvb.api.domain.member.service;

import com.tvb.api.domain.member.dto.LoginRequest;

import java.util.Optional;

public interface UserLoginService {
    public Optional<LoginRequest> login_fromGoogle();
    public Optional<LoginRequest> login_fromFacebook();
    public Optional<LoginRequest> login_fromTwitter();
    public Optional<LoginRequest> login_fromNaver();
    public Optional<LoginRequest> login_fromKakao();

    public Optional<LoginRequest> login_fromEmail();
}
