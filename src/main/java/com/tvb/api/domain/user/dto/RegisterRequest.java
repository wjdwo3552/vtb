package com.tvb.api.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "UID는 필수입니다.")
    @Size(max = 50, message = "UID는 최대 50자여야 합니다.")
    private String uid;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 맞지 않습니다.")
    @Size(max = 100, message = "이메일은 최대 100자여야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 6, max = 255, message = "비밀번호는 최소 6자, 최대 255자여야 합니다.")
    private String password;

    @Size(max = 20, message = "이름은 최대 20자여야 합니다.")
    private String name;

    @Size(max = 20, message = "전화번호는 최대 20자여야 합니다.")
    private String phone;

    @Size(max = 20, message = "등급은 최대 20자여야 합니다.")
    private String grade;

    @Size(max = 30, message = "로그인 제공자는 최대 30자여야 합니다.")
    private String provider;

    private String profileImage;
}
