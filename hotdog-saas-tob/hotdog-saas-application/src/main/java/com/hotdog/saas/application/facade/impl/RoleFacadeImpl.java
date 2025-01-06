package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.role.*;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.application.entity.response.role.RoleOptionsDTO;
import com.hotdog.saas.application.facade.RoleFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.role.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleFacadeImpl extends BaseProcessor implements RoleFacade {

    private final RoleCreateProcessor roleCreateProcessor;
    private final RoleListProcessor roleListProcessor;
    private final RoleOptionsProcessor roleOptionsProcessor;
    private final RoleDetailProcessor roleDetailProcessor;
    private final RoleUpdateProcessor roleUpdateProcessor;
    private final RoleDeleteProcessor roleDeleteProcessor;
    private final RolePermissionProcessor rolePermissionProcessor;

    public RoleFacadeImpl(RoleCreateProcessor roleCreateProcessor, RoleListProcessor roleListProcessor, RoleOptionsProcessor roleOptionsProcessor, RoleDetailProcessor roleDetailProcessor, RoleUpdateProcessor roleUpdateProcessor, RoleDeleteProcessor roleDeleteProcessor, RolePermissionProcessor rolePermissionProcessor) {
        this.roleCreateProcessor = roleCreateProcessor;
        this.roleListProcessor = roleListProcessor;
        this.roleOptionsProcessor = roleOptionsProcessor;
        this.roleDetailProcessor = roleDetailProcessor;
        this.roleUpdateProcessor = roleUpdateProcessor;
        this.roleDeleteProcessor = roleDeleteProcessor;
        this.rolePermissionProcessor = rolePermissionProcessor;
    }

    @Override
    public BaseResponse<Boolean> createRole(CreateRoleRequest createRoleRequest) {
        return this.doBiz(createRoleRequest, roleCreateProcessor);
    }

    @Override
    public BaseResponse<PageResponseDTO<RoleDTO>> roleListPage(RolePageRequest rolePageRequest) {
        return this.doBiz(rolePageRequest, roleListProcessor);
    }

    @Override
    public BaseResponse<List<RoleOptionsDTO>> roleOptions(OptionsRoleRequest optionsRoleRequest) {
        return this.doBiz(optionsRoleRequest, roleOptionsProcessor);
    }

    @Override
    public BaseResponse<RoleDTO> roleDetail(QueryRoleRequest queryRoleRequest) {
        return this.doBiz(queryRoleRequest, roleDetailProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateRole(UpdateRoleRequest updateRoleRequest) {
        return this.doBiz(updateRoleRequest, roleUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> rolePermission(PermissionRoleRequest permissionRoleRequest) {
        return this.doBiz(permissionRoleRequest, rolePermissionProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteRole(DeleteRoleRequest deleteRoleRequest) {
        return this.doBiz(deleteRoleRequest, roleDeleteProcessor);
    }
}
