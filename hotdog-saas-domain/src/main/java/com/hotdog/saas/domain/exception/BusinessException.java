package com.hotdog.saas.domain.exception;

import com.hotdog.saas.domain.enums.ResultCodeEnum;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ResultCodeEnum resultCode;
    private String message;

    private BusinessException() {
    }

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        this.resultCode = resultCodeEnum;
    }

    public BusinessException(ResultCodeEnum resultCodeEnum, String message) {
        this.resultCode = resultCodeEnum;
        this.message = message;
    }
}
