package com.tvb.api.domain.user.controller;

import com.tvb.api.domain.user.dto.RegisterRequest;
import com.tvb.api.domain.user.dto.RegisterResponse;
import com.tvb.api.domain.user.service.RegisterSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterSerivce registerService;

    @PostMapping("/")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(registerService.toRegisterUser(registerRequest));
    }
}
