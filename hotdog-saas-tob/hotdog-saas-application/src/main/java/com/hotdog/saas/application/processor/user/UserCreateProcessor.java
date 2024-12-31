package com.hotdog.saas.application.processor.user;

import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.service.PasswordService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UserCreateProcessor extends AbstractUserProcessor<CreateUserRequest, BaseResponse<Boolean>> {

    private final PasswordService passwordService;

    public UserCreateProcessor(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateUserRequest request, BaseResponse<Boolean> response) {
        // 校验用户名是否存在
        super.existsByUsername(request.getUsername());
        // 保存
        User user = buildUser(request);
        userRepository.save(user);
        response.setData(Boolean.TRUE);
    }

    private User buildUser(CreateUserRequest request) {
        User user = UserAssembler.INSTANCE.convert(request);
        // 生成盐
        user.generatorSalt();
        // 生成密码
        String password = passwordService.generatorPassword(Constants.DEFAULT_PASSWORD, user.getSalt());
        user.setPassword(password);

        user.setAvatar(Constants.DEFAULT_AVATAR);
        return user;
    }

}
