package com.hotdog.saas.web.interceptor;

import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.foundation.AuthService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>token拦截器</p>
 * <li>token校验</li>
 * <li>权限校验</li>
 *
 * @author donghe.wu.o
 * @date 2024/12/16 17:39
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    private final Boolean needValidToken;

    private final AuthService authService;

    public TokenInterceptor(Boolean needValidToken, AuthService authService) {
        this.needValidToken = needValidToken;
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            if (needValidToken) {
                String token = request.getHeader(Constants.HEADER_TOKEN_KEY);
                if (StringUtils.isEmpty(token)) {
                    throw new BusinessException("token为空");
                }
                authService.verifyToken(token);
            }
            return true;
        } catch (ExpiredJwtException e) {
            throw new BusinessException(ResultCodeEnum.TOKEN_EXPIRE);
        } catch (SignatureException e) {
            throw new BusinessException(ResultCodeEnum.TOKEN_INVALID);
        } catch (IllegalArgumentException | MalformedJwtException | UnsupportedJwtException e) {
            throw new BusinessException(ResultCodeEnum.TOKEN_FAIL);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }
            log.error("token拦截器处理异常，{}", e.getMessage(), e);
            throw new BusinessException(ResultCodeEnum.FAIL);
        }
    }

}
