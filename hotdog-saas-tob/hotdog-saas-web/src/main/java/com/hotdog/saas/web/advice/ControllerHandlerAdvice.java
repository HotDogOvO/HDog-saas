package com.hotdog.saas.web.advice;

import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 * @author hotdog
 * @date 2024/12/10 17:47
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ControllerHandlerAdvice {

    /**
     * 业务异常捕获
     * @param ex 业务异常
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<Object> handleBusinessException(BusinessException ex) {
        String errorMessage = ex.getMessage();
        log.error("请求异常, code: {}, message: {}", ex.getResultCode().getCode(), errorMessage, ex);
        return new BaseResponse<>(ex.getResultCode(), errorMessage);
    }

    /**
     * 请求参数异常捕获
     * @param ex @Validated抛出的异常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        return new BaseResponse<>(ResultCodeEnum.PARAMS_INVALID, errorMessage);
    }

    /**
     * 其他异常
     * @param ex Exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse<Object> handleException(Exception ex) {
        log.error("请求异常, {}", ex.getMessage(), ex);
        return new BaseResponse<>(ResultCodeEnum.FAIL);
    }
}
