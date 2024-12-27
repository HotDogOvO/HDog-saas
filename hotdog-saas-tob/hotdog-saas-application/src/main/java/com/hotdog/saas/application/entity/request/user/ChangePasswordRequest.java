package com.hotdog.saas.application.entity.request.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.exception.BusinessException;

import org.apache.commons.lang3.StringUtils;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Override
    public void validate() {
        // 原密码与新密码校验
        if(!StringUtils.equals(newPassword, checkPassword)) {
            throw new BusinessException("新密码校验不一致，请检查后重试");
        }
    }
}
