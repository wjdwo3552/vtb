package com.tvb.api.domain.user.dto;


import com.tvb.api.domain.user.entity.auth.Password;
import com.tvb.api.domain.user.entity.member.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private User user;
    private Password password;

    public Map<String, String> getDataMap() {
        Map<String, String> data = new HashMap<>();
        data.put("userId", this.user.getUserId());
        return data;
    }
}
