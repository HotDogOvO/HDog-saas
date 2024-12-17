package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.log.login.LoginLogPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.log.login.LoginLogDTO;
import com.hotdog.saas.application.facade.LoginLogFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.log.login.LoginLogListProcessor;

import org.springframework.stereotype.Component;

@Component
public class LoginLogFacadeImpl extends BaseProcessor implements LoginLogFacade {

    private final LoginLogListProcessor loginLogListProcessor;

    public LoginLogFacadeImpl(LoginLogListProcessor loginLogListProcessor) {
        this.loginLogListProcessor = loginLogListProcessor;
    }

    @Override
    public BaseResponse<PageResponseDTO<LoginLogDTO>> loginLogListPage(LoginLogPageRequest loginLogPageRequest) {
        return this.doBiz(loginLogPageRequest, loginLogListProcessor, false, () -> "");
    }
}
