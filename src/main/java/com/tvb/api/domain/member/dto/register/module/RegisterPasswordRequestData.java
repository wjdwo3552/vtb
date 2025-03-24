package com.tvb.api.domain.member.dto.register.module;

import com.tvb.api.domain.member.entity.User;
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
