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
public class AuthRequest {
    private User user;
    private Password password;

    public Map<String, String> getDataMap() {
        Map<String, String> data = new HashMap<>();
        data.put("userId", this.user.getUserId());
        data.put("userNo", String.valueOf(this.user.getUserNo()));
        return data;
    }


    public void changeUser(User user) {
        this.user = user;
    }
}
