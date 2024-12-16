package com.hotdog.saas.application.entity.response.login;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "登录返回DTO", description = "登录返回DTO")
public class LoginDTO {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "用户账号")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "token")
    private String token;

    @Schema(description = "角色集合")
    private List<UserRoleDTO> roleList;

    @Schema(description = "权限集合")
    private List<RoleMenuDTO> menuList;

    @Data
    @Builder
    public static class RoleMenuDTO {
        @Schema(description = "权限名")
        private String name;

        @Schema(description = "权限code")
        private String permission;
    }

    @Data
    @Builder
    public static class UserRoleDTO {
        @Schema(description = "角色ID")
        private Long roleId;

        @Schema(description = "角色名")
        private String roleName;
    }

}
