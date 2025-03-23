package com.tvb.api.domain.user.exception.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessageMap {
    private String code;
    private String message;
}
