package com.tvb.api.domain.member.service;

import com.tvb.api.domain.member.dto.register.RegisterRequestData;
import com.tvb.api.domain.member.dto.register.RegisterResponse;

public interface RegisterSerivce {
    RegisterResponse toRegisterUser(RegisterRequestData registerRequestData);
}
