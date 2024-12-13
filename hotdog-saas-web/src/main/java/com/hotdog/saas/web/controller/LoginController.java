package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.login.LoginRequest;
import com.hotdog.saas.application.entity.request.login.LogoutRequest;
import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.login.LoginDTO;
import com.hotdog.saas.application.facade.LoginFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "登录管理")
@RestController
@RequestMapping("/api/hotdog/v1")
public class LoginController {

    private final LoginFacade loginFacade;

    public LoginController(LoginFacade loginFacade) {
        this.loginFacade = loginFacade;
    }

    @Operation(summary = "登录")
    @PostMapping("/login")
    public BaseResponse<LoginDTO> login(@RequestBody @Validated LoginRequest loginRequest) {
        return loginFacade.login(loginRequest);
    }

    @Operation(summary = "登出")
    @PostMapping("/logout")
    public BaseResponse<Boolean> logout(@RequestHeader("Authorization") String token) {
        LogoutRequest logoutRequest = LogoutRequest.builder()
                .token(token)
                .build();
        return loginFacade.logout(logoutRequest);
    }

}
