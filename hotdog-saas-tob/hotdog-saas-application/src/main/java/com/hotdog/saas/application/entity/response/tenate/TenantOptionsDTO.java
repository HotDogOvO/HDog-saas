package com.hotdog.saas.application.entity.response.tenate;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "租户下拉框返回DTO", description = "租户下拉框返回DTO")
public class TenantOptionsDTO {

    @Schema(description = "租户ID")
    private Long id;

    @Schema(description = "租户名")
    private String name;

}
