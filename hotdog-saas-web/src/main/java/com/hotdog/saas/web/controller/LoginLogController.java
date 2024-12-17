package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.log.login.LoginLogPageRequest;
import com.hotdog.saas.application.entity.request.login.LoginRequest;
import com.hotdog.saas.application.entity.request.login.LogoutRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.log.login.LoginLogDTO;
import com.hotdog.saas.application.entity.response.login.LoginDTO;
import com.hotdog.saas.application.facade.LoginFacade;
import com.hotdog.saas.application.facade.LoginLogFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "登录日志管理")
@RestController
@RequestMapping("/api/hotdog/v1/login/log")
public class LoginLogController {

    private final LoginLogFacade loginLogFacade;

    public LoginLogController(LoginLogFacade loginLogFacade) {
        this.loginLogFacade = loginLogFacade;
    }

    @Operation(summary = "登陆日志列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<LoginLogDTO>> loginLogListPage(@RequestBody @Validated LoginLogPageRequest loginLogPageRequest) {
        return loginLogFacade.loginLogListPage(loginLogPageRequest);
    }

}
