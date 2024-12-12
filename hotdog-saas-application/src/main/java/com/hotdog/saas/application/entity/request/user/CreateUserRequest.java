package com.hotdog.saas.application.entity.request.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "创建用户DTO", description = "创建用户DTO")
public class CreateUserRequest extends BaseRequestParam {

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID")
    private Long tenantId;

    @NotBlank(message = "用户账号不能为空")
    @Schema(description = "用户账号")
    private String username;

    @NotBlank(message = "用户昵称不能为空")
    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;

}
