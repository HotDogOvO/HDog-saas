package com.hotdog.saas.web.aspect;

import com.hotdog.saas.application.entity.request.login.LoginRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class IpAspect {

    @Before("execution(* com.hotdog.saas.web.controller.LoginController.*(..)) && args(loginRequest,..)")
    public void logUserIp(LoginRequest loginRequest) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            String ip = getClientIp(request);
            loginRequest.setLoginIp(ip);
        }
    }

    /**
     * 获取当前请求对象
     */
    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

    /**
     * 获取客户端 IP 地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        try {
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            // 如果通过代理，可能有多个 IP，取第一个有效 IP
            if (ip != null && ip.contains(",")) {
                ip = ip.split(",")[0].trim();
            }
        } catch (Exception e) {
            ip = "0.0.0.0";
            log.error("获取登录IP失败，设置为默认IP，{}", e.getMessage(), e);
        }
        return ip;
    }

}
