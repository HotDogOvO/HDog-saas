package com.hotdog.saas.application.entity.request.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "更新用户DTO", description = "更新用户DTO")
public class UpdateUserRequest extends BaseRequestParam {

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long id;

    @NotBlank(message = "用户昵称不能为空")
    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "状态")
    private Integer status;

    @Override
    public void validate() {

    }
}
