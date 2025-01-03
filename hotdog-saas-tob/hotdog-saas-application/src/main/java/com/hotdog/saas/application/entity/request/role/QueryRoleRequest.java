package com.hotdog.saas.application.entity.request.role;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "查询角色DTO", description = "查询角色DTO")
public class QueryRoleRequest extends BaseRequestParam {

    @NotNull(message = "角色ID不能为空")
    @Schema(description = "角色ID")
    private Long id;

    @Override
    public void validate() {

    }
}
