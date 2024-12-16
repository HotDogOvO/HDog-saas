package com.hotdog.saas.application.entity.request.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "更新菜单DTO", description = "更新菜单DTO")
public class UpdateMenuRequest extends BaseMenuRequest {

    @NotNull(message = "菜单ID不能为空")
    @Schema(description = "菜单ID")
    private Long id;

    @NotNull(message = "父菜单ID不能为空")
    @Schema(description = "父菜单ID")
    private Long parentId;

    @NotEmpty(message = "权限名称不能为空")
    @Schema(description = "权限名称")
    private String name;

    @NotNull(message = "显示顺序不能为空")
    @Schema(description = "显示顺序")
    private Integer sort;

    @NotEmpty(message = "菜单icon不能为空")
    @Schema(description = "菜单icon")
    private String icon;

    @Override
    public void validate() {
        super.validate();
    }
}
