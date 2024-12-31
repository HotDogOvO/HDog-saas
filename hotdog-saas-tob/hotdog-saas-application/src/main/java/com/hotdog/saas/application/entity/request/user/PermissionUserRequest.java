package com.hotdog.saas.application.entity.request.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "授权用户DTO", description = "授权用户DTO")
public class PermissionUserRequest extends BaseRequestParam {

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long id;

    @NotNull(message = "角色ID不能为空")
    @Schema(description = "角色ID")
    private List<Long> roleIdList;

    @Override
    public void validate() {
    }
}
