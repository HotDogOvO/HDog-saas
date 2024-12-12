package com.hotdog.saas.application.entity.request.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "查询租户DTO", description = "查询租户DTO")
public class QueryUserRequest extends BaseRequestParam {

    @NotNull(message = "租户ID不能为空")
    @Schema(description = "租户ID")
    private Long id;

}
