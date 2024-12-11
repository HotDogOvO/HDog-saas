package com.hotdog.saas.web.aspect;

import com.hotdog.saas.application.entity.response.BaseResponse;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ResultAspect {

    @AfterReturning(
            pointcut = "execution(* com.hotdog.saas.web.controller.*.*(..)) || execution(* com.hotdog.saas.web.advice.*.*(..))",
            returning = "result")
    public void logBeforeMethod(Object result) {
        if (result instanceof BaseResponse<?> baseResponse) {
            // 请求时间戳
            baseResponse.setTimestamp(System.currentTimeMillis() / 1000);

            // 请求url
            try {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();
                    baseResponse.setPath(request.getRequestURI());
                }
            } catch (Exception e) {
                log.error("获取请求url失败，原因是：{}", e.getMessage(), e);
            }
        }
    }

}
