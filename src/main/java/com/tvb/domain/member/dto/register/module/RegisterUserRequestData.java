package com.tvb.domain.member.dto.register.module;

import com.tvb.domain.member.domain.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestData {
    private String userId;
    LoginType loginType;
}
