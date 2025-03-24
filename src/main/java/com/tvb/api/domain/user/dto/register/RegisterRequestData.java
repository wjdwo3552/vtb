package com.tvb.api.domain.user.dto.register;

import com.tvb.api.domain.user.dto.register.module.RegisterPasswordRequestData;
import com.tvb.api.domain.user.dto.register.module.RegisterProfileRequestData;
import com.tvb.api.domain.user.dto.register.module.RegisterUserRequestData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestData {
    private RegisterUserRequestData user;
    private RegisterProfileRequestData profile;
    private RegisterPasswordRequestData password;
}
