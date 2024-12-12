package com.hotdog.saas.application.entity.request.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "删除用户DTO", description = "删除用户DTO")
public class DeleteUserRequest extends BaseRequestParam {

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long id;

}
