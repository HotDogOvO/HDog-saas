package com.hotdog.saas.application.processor.login;

import com.hotdog.saas.application.entity.request.login.LogoutRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.foundation.AuthService;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogoutProcessor extends AbstractLoginProcessor<LogoutRequest, BaseResponse<Boolean>> {

    private final AuthService authService;

    public LogoutProcessor(AuthService authService) {
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
        String username = authService.extractUsername();
        log.info("系统登出，登出人：{}", username);
        super.removeToken(username);
        response.setData(Boolean.TRUE);
    }
}
