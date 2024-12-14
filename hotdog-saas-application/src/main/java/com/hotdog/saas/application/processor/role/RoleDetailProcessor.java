package com.hotdog.saas.application.processor.role;

import com.hotdog.saas.application.assembler.RoleAssembler;
import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.role.QueryRoleRequest;
import com.hotdog.saas.application.entity.request.user.QueryUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RoleDetailProcessor extends AbstractRoleProcessor<QueryRoleRequest, BaseResponse<RoleDTO>> {

    @Override
    public BaseResponse<RoleDTO> initResult() {
        BaseResponse<RoleDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(QueryRoleRequest request, BaseResponse<RoleDTO> response) {
        Long userId = request.getId();
        super.exists(userId);
        Role role = roleRepository.findById(userId);
        RoleDTO roleDTO = RoleAssembler.INSTANCE.convertToDTO(role);
        response.setData(roleDTO);
    }

}
