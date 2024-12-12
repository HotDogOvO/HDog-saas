package com.hotdog.saas.application.processor.user;

import com.hotdog.saas.application.assembler.TenantAssembler;
import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.tenant.AbstractTenantProcessor;
import com.hotdog.saas.domain.config.ProjectConfig;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UserCreateProcess extends AbstractUserProcessor<CreateUserRequest, BaseResponse<Boolean>> {

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
        Integer saveFlag = userRepository.save(user);
        response.setData(checkFlag(saveFlag));
    }

    private User buildUser(CreateUserRequest request) {
        User user = UserAssembler.INSTANCE.convert(request);
        String password = SignUtils.sha256(Constants.DEFAULT_PASSWORD);
        user.setPassword(password);
        user.setAvatar(Constants.DEFAULT_AVATAR);

        return user;
    }

}
