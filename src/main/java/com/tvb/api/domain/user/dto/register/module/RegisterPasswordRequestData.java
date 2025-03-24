package com.tvb.api.domain.user.dto.register.module;

import com.tvb.api.domain.user.entity.member.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPasswordRequestData {
    private String password;
    private LocalDateTime updatedAt;
    private User user;
}
