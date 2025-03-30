package com.tvb.api.domain.member.controller;

import com.tvb.api.domain.member.dto.register.RegisterRequestData;
import com.tvb.api.domain.member.dto.register.RegisterResponse;
import com.tvb.api.domain.member.service.RegisterSerivce;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequestData registerRequestData) {
        return ResponseEntity.ok(registerService.toRegisterUser(registerRequestData));
    }
}
