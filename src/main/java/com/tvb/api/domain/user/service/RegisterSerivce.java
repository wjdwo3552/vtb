package com.tvb.api.domain.user.service;

import com.tvb.api.domain.user.dto.register.RegisterRequestData;
import com.tvb.api.domain.user.dto.RegisterResponse;

public interface RegisterSerivce {
    RegisterResponse toRegisterUser(RegisterRequestData registerRequestData);
}
