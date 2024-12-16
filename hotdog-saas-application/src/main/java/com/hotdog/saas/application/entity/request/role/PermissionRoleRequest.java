package com.hotdog.saas.application.entity.request.role;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "授权角色DTO", description = "授权角色DTO")
public class PermissionRoleRequest extends BaseRequestParam {

    @NotNull(message = "角色ID不能为空")
    @Schema(description = "角色ID")
    private Long id;

    @NotNull(message = "菜单ID不能为空")
    @Schema(description = "菜单ID")
    private List<Long> menuIdList;

    @Override
    public void validate() {
    }
}
