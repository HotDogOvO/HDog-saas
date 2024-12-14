package com.hotdog.saas.application.processor.login;

import com.hotdog.saas.application.entity.request.login.LogoutRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.foundation.AuthService;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogoutProcessor extends AbstractLoginProcessor<LogoutRequest, BaseResponse<Boolean>> {

    private final RedisCacheService redisCacheService;

    private final AuthService authService;

    public LogoutProcessor(RedisCacheService redisCacheService, AuthService authService) {
        this.redisCacheService = redisCacheService;
        this.authService = authService;
    }

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(LogoutRequest request, BaseResponse<Boolean> response) {
        String token = request.getToken();
        authService.verifyToken(token);
        String username = authService.extractUsername(token);
        if (StringUtils.isNotEmpty(username)) {
            redisCacheService.delete(RedisConstants.getUserKey(username));
        }

        response.setData(Boolean.TRUE);
    }
}
