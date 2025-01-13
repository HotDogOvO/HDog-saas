package com.hotdog.saas.application.entity.request.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.constant.Constants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "创建用户DTO", description = "创建用户DTO")
public class CreateUserRequest extends BaseRequestParam {

    @NotNull(message = "租户ID不能为空")
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
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "手机号")
    @Pattern(regexp = Constants.PHONE_REGULAR_EXPRESSION, message = "手机号格式不正确")
    private String mobile;

    @Override
    public void validate() {

    }
}
