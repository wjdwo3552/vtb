package com.tvb.domain.member.service;

import com.tvb.domain.member.dto.register.RegisterRequestData;
import com.tvb.domain.member.dto.register.RegisterResponse;

public interface RegisterService {
    RegisterResponse toRegisterUser(RegisterRequestData registerRequestData);
}
