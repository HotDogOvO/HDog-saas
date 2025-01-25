package com.hotdog.saas.matrix.web.controller;

import com.hotdog.saas.application.entity.request.role.*;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.application.entity.response.role.RoleOptionsDTO;
import com.hotdog.saas.application.facade.RoleFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "角色管理")
@RestController
@RequestMapping("/api/hotdog/v1/role")
public class RoleController {

    private final RoleFacade roleFacade;

    public RoleController(RoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }

    @Operation(summary = "创建角色")
    @PostMapping("/create")
    public BaseResponse<Boolean> createRole(@RequestBody @Validated CreateRoleRequest createRoleRequest) {
        return roleFacade.createRole(createRoleRequest);
    }

    @Operation(summary = "查询角色分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<RoleDTO>> roleListPage(@RequestBody @Validated RolePageRequest RolePageRequest) {
        return roleFacade.roleListPage(RolePageRequest);
    }

    @Operation(summary = "查询角色下拉框")
    @PostMapping("/options")
    public BaseResponse<List<RoleOptionsDTO>> roleOptions(@RequestBody @Validated OptionsRoleRequest optionsRoleRequest) {
        return roleFacade.roleOptions(optionsRoleRequest);
    }

    @Operation(summary = "查询角色详情")
    @PostMapping("/detail")
    public BaseResponse<RoleDTO> roleDetail(@RequestBody @Validated QueryRoleRequest queryRoleRequest) {
        return roleFacade.roleDetail(queryRoleRequest);
    }

    @Operation(summary = "更新角色")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateRole(@RequestBody @Validated UpdateRoleRequest updateRoleRequest) {
        return roleFacade.updateRole(updateRoleRequest);
    }

    @Operation(summary = "授权")
    @PostMapping("/permission")
    public BaseResponse<Boolean> rolePermission(@RequestBody @Validated PermissionRoleRequest permissionRoleRequest) {
        return roleFacade.rolePermission(permissionRoleRequest);
    }

    @Operation(summary = "删除角色")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteRole(@RequestBody @Validated DeleteRoleRequest deleteRoleRequest) {
        return roleFacade.deleteRole(deleteRoleRequest);
    }

}
