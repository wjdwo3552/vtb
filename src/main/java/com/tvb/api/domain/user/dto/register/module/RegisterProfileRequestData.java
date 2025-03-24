package com.tvb.api.domain.user.dto.register.module;

import com.tvb.api.domain.user.entity.member.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProfileRequestData {
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
}
