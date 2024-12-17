package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.login.LoginRequest;
import com.hotdog.saas.application.entity.request.login.LogoutRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.login.LoginDTO;
import com.hotdog.saas.application.facade.LoginFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.login.LoginProcessor;
import com.hotdog.saas.application.processor.login.LogoutProcessor;

import org.springframework.stereotype.Component;

@Component
public class LoginFacadeImpl extends BaseProcessor implements LoginFacade {

    private final LoginProcessor loginProcessor;
    private final LogoutProcessor logoutProcessor;

    public LoginFacadeImpl(LoginProcessor loginProcessor, LogoutProcessor logoutProcessor) {
        this.loginProcessor = loginProcessor;
        this.logoutProcessor = logoutProcessor;
    }

    @Override
    public BaseResponse<LoginDTO> login(LoginRequest loginRequest) {
        return this.doBiz(loginRequest, loginProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> logout(LogoutRequest logoutRequest) {
        return this.doBiz(logoutRequest, logoutProcessor, false, () -> "");
    }
}
