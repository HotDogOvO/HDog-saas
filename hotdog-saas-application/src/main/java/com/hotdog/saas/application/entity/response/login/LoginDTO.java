package com.hotdog.saas.application.entity.response.login;

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

}
