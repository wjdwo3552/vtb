package com.tvb.api.domain.user.service;

import com.tvb.api.domain.user.dto.RegisterRequest;
import com.tvb.api.domain.user.dto.RegisterResponse;
import com.tvb.api.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

public interface RegisterSerivce {
    RegisterResponse toRegisterUser(RegisterRequest registerRequest);
}
