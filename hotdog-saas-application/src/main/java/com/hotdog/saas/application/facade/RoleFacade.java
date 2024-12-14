package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.role.*;
import com.hotdog.saas.application.entity.request.user.*;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RoleFacade {
    BaseResponse<Boolean> createRole(CreateRoleRequest createRoleRequest);

    BaseResponse<PageResponseDTO<RoleDTO>> roleListPage(RolePageRequest RolePageRequest);

    BaseResponse<RoleDTO> roleDetail(QueryRoleRequest queryRoleRequest);

    BaseResponse<Boolean> updateRole(UpdateRoleRequest updateRoleRequest);

    BaseResponse<Boolean> deleteRole(DeleteRoleRequest deleteRoleRequest);
}
