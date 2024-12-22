package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.login.LoginRequest;
import com.hotdog.saas.application.entity.request.login.LogoutRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.login.LoginDTO;

public interface LoginFacade {

    /**
     * 登录
     * @param loginRequest loginRequest
     * @return 登录用户
     */
    BaseResponse<LoginDTO> login(LoginRequest loginRequest);

    /**
     * 登出
     * @param logoutRequest logoutRequest
     * @return
     */
    BaseResponse<Boolean> logout(LogoutRequest logoutRequest);
}
