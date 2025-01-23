package com.hotdog.saas.domain.exception;

import com.hotdog.saas.domain.enums.ResultCodeEnum;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final ResultCodeEnum resultCode;
    private final String message;

    public AuthException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getCode());
        this.resultCode = resultCodeEnum;
        this.message = resultCodeEnum.getMessage();
    }

    public AuthException(ResultCodeEnum resultCodeEnum, String message) {
        super(resultCodeEnum.getCode());
        this.resultCode = resultCodeEnum;
        this.message = message;
    }

    public AuthException(String message) {
        super(ResultCodeEnum.FAIL.getCode());
        this.message = message;
        this.resultCode = ResultCodeEnum.FAIL;
    }
}
