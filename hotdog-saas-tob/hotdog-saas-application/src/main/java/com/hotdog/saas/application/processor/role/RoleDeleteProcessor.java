package com.hotdog.saas.application.processor.role;

import com.hotdog.saas.application.entity.request.role.DeleteRoleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RoleDeleteProcessor extends AbstractRoleProcessor<DeleteRoleRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteRoleRequest request, BaseResponse<Boolean> response) {
        super.exists(request.getId());
        Integer removeFlag = roleRepository.remove(request.getId(), request.getOperator());
        response.setData(checkFlag(removeFlag));
    }

}
