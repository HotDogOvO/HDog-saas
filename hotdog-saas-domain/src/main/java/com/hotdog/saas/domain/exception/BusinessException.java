package com.hotdog.saas.domain.exception;

import com.hotdog.saas.domain.enums.ResultCodeEnum;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ResultCodeEnum resultCode;
    private String message;

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getCode());
        this.resultCode = resultCodeEnum;
    }

    public BusinessException(ResultCodeEnum resultCodeEnum, String message) {
        super(resultCodeEnum.getCode());
        this.resultCode = resultCodeEnum;
        this.message = message;
    }

    public BusinessException(String message) {
        super(ResultCodeEnum.FAIL.getCode());
        this.message = message;
        this.resultCode = ResultCodeEnum.FAIL;
    }
}
