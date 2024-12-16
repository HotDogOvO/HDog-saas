package com.hotdog.saas.application.entity.response.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(name = "角色返回DTO", description = "角色返回DTO")
public class RoleDTO {

    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "角色名")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态（0正常 1停用）")
    private Integer status;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "角色权限集合")
    private List<RoleMenuDTO> roleMenuList;

    @Data
    @Builder
    public static class RoleMenuDTO {
        @Schema(description = "权限名")
        private String name;

        @Schema(description = "权限code")
        private String permission;
    }
}
