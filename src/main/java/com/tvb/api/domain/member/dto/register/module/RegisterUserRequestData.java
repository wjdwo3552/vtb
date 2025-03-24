package com.tvb.api.domain.member.dto.register.module;

import com.tvb.api.domain.member.entity.member.logintype.LoginType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestData {
    @Email
    @NotBlank
    @Size(max = 255)
    private String userId;

    LoginType loginType;
}
