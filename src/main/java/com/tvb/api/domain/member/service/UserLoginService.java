package com.tvb.api.domain.member.service;

import com.tvb.api.domain.member.dto.AuthRequest;

import java.util.Optional;

public interface UserLoginService {
    public Optional<AuthRequest> login_fromGoogle();
    public Optional<AuthRequest> login_fromFacebook();
    public Optional<AuthRequest> login_fromTwitter();
    public Optional<AuthRequest> login_fromNaver();
    public Optional<AuthRequest> login_fromKakao();

    public Optional<AuthRequest> login_fromEmail();
}
