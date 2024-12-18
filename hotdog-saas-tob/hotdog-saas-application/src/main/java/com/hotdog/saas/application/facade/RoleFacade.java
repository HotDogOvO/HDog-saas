package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.role.*;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.role.RoleDTO;

public interface RoleFacade {
    BaseResponse<Boolean> createRole(CreateRoleRequest createRoleRequest);

    BaseResponse<PageResponseDTO<RoleDTO>> roleListPage(RolePageRequest RolePageRequest);

    BaseResponse<RoleDTO> roleDetail(QueryRoleRequest queryRoleRequest);

    BaseResponse<Boolean> updateRole(UpdateRoleRequest updateRoleRequest);

    BaseResponse<Boolean> rolePermission(PermissionRoleRequest permissionRoleRequest);

    BaseResponse<Boolean> deleteRole(DeleteRoleRequest deleteRoleRequest);
}