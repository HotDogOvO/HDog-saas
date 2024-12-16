package com.hotdog.saas.application.processor.login;

import com.hotdog.saas.application.assembler.LoginAssembler;
import com.hotdog.saas.application.entity.request.login.LoginRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.login.LoginDTO;
import com.hotdog.saas.domain.foundation.AuthService;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Login;
import com.hotdog.saas.domain.repository.LoginRepository;
import com.hotdog.saas.domain.repository.UserRepository;
import com.hotdog.saas.domain.service.PasswordService;

import org.springframework.stereotype.Component;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginProcessor extends AbstractLoginProcessor<LoginRequest, BaseResponse<LoginDTO>> {

    private final LoginRepository loginRepository;

    private final PasswordService passwordService;

    private final AuthService authService;

    public LoginProcessor(LoginRepository loginRepository, PasswordService passwordService, AuthService authService) {
        this.loginRepository = loginRepository;
        this.passwordService = passwordService;
        this.authService = authService;
    }

    @Override
    public BaseResponse<LoginDTO> initResult() {
        BaseResponse<LoginDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(LoginRequest request, BaseResponse<LoginDTO> response) {
        // 校验用户是否存在
        existsLogin(request.getUsername());
        LoginDTO loginDTO = redisCacheService.get(RedisConstants.getUserKey(request.getUsername()), LoginDTO.class);
        if (loginDTO == null) {
            Login loginUser = loginRepository.findLoginUser(request.getUsername());

            Boolean loginFlag = passwordService.checkPassword(loginUser.getPassword(), request.getPassword(), loginUser.getSalt());
            if (!loginFlag) {
                throw new BusinessException("用户名或密码错误，请检查后重试");
            }

            loginDTO = buildLoginDTO(loginUser);
            // 写入redis
            redisCacheService.set(RedisConstants.getUserKey(loginDTO.getUsername()), loginDTO, RedisConstants.USER_TOKEN_TTL);
        }

        response.setData(loginDTO);
    }

    private LoginDTO buildLoginDTO(Login loginUser) {
        LoginDTO loginDTO = LoginAssembler.INSTANCE.convert(loginUser);
        loginDTO.setToken(authService.generateToken(loginUser));
        return loginDTO;
    }

    private void existsLogin(String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        Long nameCount = userRepository.existsByUsername(username);
        if (nameCount == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "用户名不存在");
        }
    }

}
