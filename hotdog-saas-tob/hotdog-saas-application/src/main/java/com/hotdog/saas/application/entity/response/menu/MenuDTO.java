package com.hotdog.saas.application.entity.response.menu;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "菜单返回DTO", description = "菜单返回DTO")
public class MenuDTO {

    @Schema(description = "菜单ID")
    private Long id;

    @Schema(description = "父菜单ID")
    private Long parentId;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "显示顺序")
    private Integer sort;

    @Schema(description = "菜单icon")
    private String icon;

    @Schema(description = "权限标识")
    private String permission;

    @Schema(description = "菜单类型（1：菜单 2：按钮）")
    private Integer type;

    @Schema(description = "路由地址")
    private String path;
    
    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "子菜单")
    private List<MenuDTO> children;
}
