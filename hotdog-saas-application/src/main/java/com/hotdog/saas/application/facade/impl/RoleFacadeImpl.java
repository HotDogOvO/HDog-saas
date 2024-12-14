package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.role.*;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.application.facade.RoleFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.role.*;
import org.springframework.stereotype.Component;

@Component
public class RoleFacadeImpl extends BaseProcessor implements RoleFacade {

    private final RoleCreateProcessor roleCreateProcessor;
    private final RoleListProcessor roleListProcessor;
    private final RoleDetailProcessor roleDetailProcessor;
    private final RoleUpdateProcessor roleUpdateProcessor;
    private final RoleDeleteProcessor roleDeleteProcessor;

    public RoleFacadeImpl(RoleCreateProcessor roleCreateProcessor, RoleListProcessor roleListProcessor, RoleDetailProcessor roleDetailProcessor, RoleUpdateProcessor roleUpdateProcessor, RoleDeleteProcessor roleDeleteProcessor) {
        this.roleCreateProcessor = roleCreateProcessor;
        this.roleListProcessor = roleListProcessor;
        this.roleDetailProcessor = roleDetailProcessor;
        this.roleUpdateProcessor = roleUpdateProcessor;
        this.roleDeleteProcessor = roleDeleteProcessor;
    }

    @Override
    public BaseResponse<Boolean> createRole(CreateRoleRequest createRoleRequest) {
        return this.doBiz(createRoleRequest, roleCreateProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<PageResponseDTO<RoleDTO>> roleListPage(RolePageRequest rolePageRequest) {
        return this.doBiz(rolePageRequest, roleListProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<RoleDTO> roleDetail(QueryRoleRequest queryRoleRequest) {
        return this.doBiz(queryRoleRequest, roleDetailProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> updateRole(UpdateRoleRequest updateRoleRequest) {
        return this.doBiz(updateRoleRequest, roleUpdateProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> deleteRole(DeleteRoleRequest deleteRoleRequest) {
        return this.doBiz(deleteRoleRequest, roleDeleteProcessor, false, () -> "");
    }
}
