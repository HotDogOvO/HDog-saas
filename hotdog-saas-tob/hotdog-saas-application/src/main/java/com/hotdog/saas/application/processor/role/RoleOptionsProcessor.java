package com.hotdog.saas.application.processor.role;

import com.hotdog.saas.application.assembler.RoleAssembler;
import com.hotdog.saas.application.entity.request.role.OptionsRoleRequest;
import com.hotdog.saas.application.entity.request.role.RolePageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.application.entity.response.role.RoleOptionsDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RoleOptionsProcessor extends AbstractRoleProcessor<OptionsRoleRequest, BaseResponse<List<RoleOptionsDTO>>> {

    @Override
    public BaseResponse<List<RoleOptionsDTO>> initResult() {
        BaseResponse<List<RoleOptionsDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(OptionsRoleRequest request, BaseResponse<List<RoleOptionsDTO>> response) {
        Role role = RoleAssembler.INSTANCE.convert(request);

        List<Role> list = roleRepository.findList(role);

        List<RoleOptionsDTO> roleDTOList = RoleAssembler.INSTANCE.convertOptions(list);
        response.setData(roleDTOList);
    }

}
