package com.hotdog.saas.matrix.domain.utils;

import com.hotdog.saas.matrix.domain.constant.Constants;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 网络工具类
 * @author hotdog
 * @date 2024/12/17 11:40
 */
public class NetworkUtils {

    /**
     * 获取spring-request
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * 获取请求的user-agent
     * @return userAgent
     */
    public static String getUserAgent() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return getUserAgent(request);
    }

    private static String getUserAgent(HttpServletRequest request) {
        String ua = request.getHeader(Constants.HEADER_USER_AGENT_KEY);
        return ua != null ? ua : StringUtils.EMPTY;
    }

    /**
     * 获取请求IP
     * @return ip
     */
    public static String getClientIP() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return getClientIP(request);
    }

    private static String getClientIP(HttpServletRequest request) {
        return getClientIPByHeader(request, Constants.HEADER_IP_KEY);
    }

    private static String getClientIPByHeader(HttpServletRequest request, String[] headerNames) {
        for (String header : headerNames) {
            String ip = request.getHeader(header);
            if (!isUnknown(ip)) {
                return getMultistageReverseProxyIp(ip);
            }
        }

        String ip = request.getRemoteAddr();
        return getMultistageReverseProxyIp(ip);
    }

    private static boolean isUnknown(String checkString) {
        return StringUtils.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
    }

    /**
     * 解析本地IP
     * copy for hutool utils
     * @param ip ip
     * @return 本地IP
     */
    private static String getMultistageReverseProxyIp(String ip) {
        if (ip != null && StringUtils.indexOf(ip, ',') > 0) {
            for (String subIp : StringUtils.split(ip, ',')) {
                if (!isUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

}
