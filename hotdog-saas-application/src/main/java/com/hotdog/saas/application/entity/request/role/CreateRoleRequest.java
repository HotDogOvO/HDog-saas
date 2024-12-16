package com.hotdog.saas.application.entity.request.role;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "创建角色DTO", description = "创建角色DTO")
public class CreateRoleRequest extends BaseRequestParam {

    @NotNull(message = "租户ID不能为空")
    @Schema(description = "租户ID")
    private Long tenantId;

    @NotBlank(message = "角色名不能为空")
    @Schema(description = "角色名")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Override
    public void validate() {

    }
}
