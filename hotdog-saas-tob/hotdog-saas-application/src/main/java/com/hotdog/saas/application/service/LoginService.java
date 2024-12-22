package com.hotdog.saas.application.service;

import com.hotdog.saas.domain.enums.log.LogSuccessEnum;
import com.hotdog.saas.domain.enums.log.LogTypeEnum;
import com.hotdog.saas.domain.model.LoginLog;

public interface LoginService {

    /**
     * 创建登录日志（异步）
     *
     * @param loginLog 登录日志对象
     */
    void createLoginLog(LoginLog loginLog);

}
