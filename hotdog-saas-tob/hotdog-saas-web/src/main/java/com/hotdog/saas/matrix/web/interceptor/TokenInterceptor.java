package com.hotdog.saas.matrix.web.interceptor;

import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.AuthException;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.foundation.AuthService;
import com.hotdog.saas.domain.utils.HttpUtils;

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
 * @author hotdog
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
                // post请求才进行校验，目前只开放Post、Options两种请求
                if (HttpUtils.validHttpPost(request.getMethod())) {
                    String token = authService.extractToken();
                    if (StringUtils.isEmpty(token)) {
                        throw new AuthException("token为空");
                    }
                    authService.verifyToken(token);
                }
            }
            return true;
        } catch (ExpiredJwtException e) {
            throw new AuthException(ResultCodeEnum.TOKEN_EXPIRE);
        } catch (SignatureException e) {
            throw new AuthException(ResultCodeEnum.TOKEN_INVALID);
        } catch (IllegalArgumentException | MalformedJwtException | UnsupportedJwtException e) {
            throw new AuthException(ResultCodeEnum.TOKEN_FAIL);
        } catch (Exception e) {
            if (e instanceof AuthException) {
                throw (AuthException) e;
            }
            log.error("token拦截器处理异常，{}", e.getMessage(), e);
            throw new BusinessException(ResultCodeEnum.FAIL);
        }
    }

}
