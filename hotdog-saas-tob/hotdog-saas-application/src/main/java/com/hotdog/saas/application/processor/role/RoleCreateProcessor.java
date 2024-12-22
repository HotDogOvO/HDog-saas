package com.hotdog.saas.application.processor.role;

import com.hotdog.saas.application.assembler.RoleAssembler;
import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.role.CreateRoleRequest;
import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RoleCreateProcessor extends AbstractRoleProcessor<CreateRoleRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateRoleRequest request, BaseResponse<Boolean> response) {
        super.existsByName(request.getName());
        Role role = RoleAssembler.INSTANCE.convert(request);
        Integer saveFlag = roleRepository.save(role);
        response.setData(checkFlag(saveFlag));
    }

}
