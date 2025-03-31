package com.tvb.api.security.jwt.auth;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class UserPrincipal implements Principal {
    private final String uid;
    @Override
    public String getName() {
        return uid;
    }
}
