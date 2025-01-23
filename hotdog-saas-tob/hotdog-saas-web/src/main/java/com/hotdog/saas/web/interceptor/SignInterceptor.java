package com.hotdog.saas.web.interceptor;


import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.domain.utils.HttpUtils;
import com.hotdog.saas.domain.utils.SignUtils;
import com.hotdog.saas.web.filter.PostRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>签名拦截器</p>
 * <li>请求类型校验</li>
 * <li>时间戳校验</li>
 * <li>签名校验</li>
 *
 * @author hotdog
 * @date 2024/12/10 17:48
 */
@Slf4j
public class SignInterceptor implements HandlerInterceptor {

    private final String secret;

    private final Boolean needValidSign;

    public SignInterceptor(String secret, Boolean needValidSign) {
        this.secret = secret;
        this.needValidSign = needValidSign;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            if (needValidSign) {
                // 请求方法校验
                checkMethod(request);
                // post请求才进行校验，目前只开放Post、Options两种请求
                if (HttpUtils.validHttpPost(request.getMethod())) {
                    // 时间戳校验
                    checkTimestamp(request);
                    // 请求参数校验
                    checkBody(request);
                }
            }
            return true;
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }
            log.error("请求拦截器处理异常，{}", e.getMessage(), e);
            throw new BusinessException(ResultCodeEnum.FAIL);
        }
    }

    private void checkMethod(HttpServletRequest request) {
        if (!HttpUtils.validHttpMethod(request.getMethod())) {
            throw new BusinessException(ResultCodeEnum.HTTP_METHOD_INVALID);
        }
    }

    private void checkTimestamp(HttpServletRequest request) {
        String timestampStr = request.getHeader("X-Timestamp");
        if (StringUtils.isBlank(timestampStr)) {
            throw new BusinessException(ResultCodeEnum.TIMESTAMP_EMPTY);
        }
        long timestamp = Long.parseLong(timestampStr);
        LocalDateTime requestTimestamp = DateUtils.getDateBySecond(timestamp);
        LocalDateTime tenMinutesAgo = DateUtils.getTenMinutesAgo(DateUtils.now());
        if (requestTimestamp.isBefore(tenMinutesAgo) || requestTimestamp.isAfter(DateUtils.now())) {
            throw new BusinessException(ResultCodeEnum.TIMESTAMP_INVALID);
        }
    }

    private void checkBody(HttpServletRequest request) throws IOException {
        String sign = request.getHeader("X-Sign");
        if (StringUtils.isBlank(sign)) {
            throw new BusinessException(ResultCodeEnum.SIGN_EMPTY);
        }

        String requestBody = getRequestBody(request);
        String backendSign = SignUtils.sign(requestBody, secret);
        if (!StringUtils.equals(backendSign, sign)) {
            throw new BusinessException(ResultCodeEnum.SIGN_FAIL);
        }
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        if (!(request instanceof PostRequestWrapper)) {
            request = new PostRequestWrapper(request);
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line.trim());
            }
        }
        return stringBuilder.toString();
    }

}
