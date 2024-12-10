package com.hotdog.saas.web.advice;

import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 * @author hotdog
 * @date 2024/12/10 17:47
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<Object> handleBusinessException(BusinessException ex) {
        log.error("请求异常, code: {}, message: {}", ex.getResultCode().getCode(), ex.getResultCode().getMessage(), ex);
        return new BaseResponse<>(ex.getResultCode());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<Object> handleException(Exception ex) {
        log.error("请求异常, {}", ex.getMessage(), ex);
        return new BaseResponse<>(ResultCodeEnum.FAIL);
    }
}
