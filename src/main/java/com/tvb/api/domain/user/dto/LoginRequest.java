package com.tvb.api.domain.user.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
    private String provider;
    private LocalDateTime createdAt;

    public Map<String, String> getDataMap() {
        Map<String, String> data = new HashMap<>();
        data.put("email", this.email);
        data.put("password", this.password);
        data.put("provider", this.provider);
        //data.put("createdAt", String.valueOf(this.createdAt));
        return data;
    }
}
