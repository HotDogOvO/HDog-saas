package com.hotdog.saas.application.entity.request.tenate;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "创建租户请求DTO", description = "创建租户请求DTO")
public class DeleteTenantRequest extends BaseRequestParam {

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID")
    private Long id;

}
