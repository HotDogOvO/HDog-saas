package com.hotdog.saas.application.entity.request.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "更新用户DTO", description = "更新用户DTO")
public class ChangePasswordRequest extends BaseRequestParam {

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long id;

    @NotNull(message = "原密码不能为空")
    @Schema(description = "原密码")
    private String oldPassword;

    @NotNull(message = "新密码不能为空")
    @Schema(description = "新密码")
    private String newPassword;

    @NotNull(message = "校验密码不能为空")
    @Schema(description = "校验密码")
    private String checkPassword;

}
