package com.tvb.api.domain.member.dto;


import com.tvb.api.domain.member.entity.Password;
import com.tvb.api.domain.member.entity.User;
import lombok.*;

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
