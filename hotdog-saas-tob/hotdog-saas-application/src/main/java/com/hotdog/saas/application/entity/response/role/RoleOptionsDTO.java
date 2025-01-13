package com.hotdog.saas.application.entity.response.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "角色下拉框返回DTO", description = "角色下拉框返回DTO")
public class RoleOptionsDTO {

    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "角色名")
    private String name;

    @Schema(description = "状态（0正常 1停用）")
    private Integer status;

}
