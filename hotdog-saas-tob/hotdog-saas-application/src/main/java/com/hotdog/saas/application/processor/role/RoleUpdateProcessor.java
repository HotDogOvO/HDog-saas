package com.hotdog.saas.application.processor.role;

import com.hotdog.saas.application.assembler.RoleAssembler;
import com.hotdog.saas.application.entity.request.role.UpdateRoleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RoleUpdateProcessor extends AbstractRoleProcessor<UpdateRoleRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(UpdateRoleRequest request, BaseResponse<Boolean> response) {
        super.exists(request.getId());
        Role role = RoleAssembler.INSTANCE.convert(request);
        Integer modifyFlag = roleRepository.modify(role);
        response.setData(checkFlag(modifyFlag));
    }

}
