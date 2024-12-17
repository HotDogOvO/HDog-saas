package com.hotdog.saas.application.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.hotdog.saas.application.service.LoginService;
import com.hotdog.saas.domain.enums.log.LogSuccessEnum;
import com.hotdog.saas.domain.enums.log.LogTypeEnum;
import com.hotdog.saas.domain.foundation.AuthService;
import com.hotdog.saas.domain.model.LoginLog;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.repository.LoginLogRepository;
import com.hotdog.saas.domain.repository.UserRepository;
import com.hotdog.saas.domain.utils.ConcurrentUtils;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.domain.utils.NetworkUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final LoginLogRepository loginLogRepository;

    public LoginServiceImpl(UserRepository userRepository, LoginLogRepository loginLogRepository) {
        this.userRepository = userRepository;
        this.loginLogRepository = loginLogRepository;
    }

    @Override
    public void createLoginLog(LoginLog loginLog) {
        ConcurrentUtils.async(() -> {
            log.info("用户登录，记录登录日志，登录用户: {}", JSONObject.toJSONString(loginLog));
            // 记录用户最后登录时间
            saveLoginIpAndDate(loginLog.getUserId(), loginLog.getUsername());
            // 记录登录日志
            loginLogRepository.save(loginLog);
        });
    }

    private void saveLoginIpAndDate(Long userId, String operator) {
        LocalDateTime now = DateUtils.now();
        User user = User.builder().id(userId)
                .loginIp(NetworkUtils.getClientIP()).loginDate(now)
                .updater(operator).updateTime(now)
                .build();
        userRepository.modify(user);
    }

}
