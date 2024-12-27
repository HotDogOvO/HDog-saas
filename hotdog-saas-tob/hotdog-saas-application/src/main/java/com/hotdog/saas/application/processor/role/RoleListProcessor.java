package com.hotdog.saas.application.processor.role;

import com.hotdog.saas.application.assembler.RoleAssembler;
import com.hotdog.saas.application.entity.request.role.RolePageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RoleListProcessor extends AbstractRoleProcessor<RolePageRequest, BaseResponse<PageResponseDTO<RoleDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<RoleDTO>> initResult() {
        BaseResponse<PageResponseDTO<RoleDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(RolePageRequest request, BaseResponse<PageResponseDTO<RoleDTO>> response) {
        request.initPage();
        Role role = RoleAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<Role>> listPageResponse = roleRepository.listPage(role, pageRequest);

        PageResponseDTO<RoleDTO> userPageResponseDTO = RoleAssembler.INSTANCE.convertPage(listPageResponse);
        response.setData(userPageResponseDTO);
    }

}
