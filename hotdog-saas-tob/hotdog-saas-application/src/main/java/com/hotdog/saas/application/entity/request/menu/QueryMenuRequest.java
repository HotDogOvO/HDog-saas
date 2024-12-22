package com.hotdog.saas.application.entity.request.menu;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "查询菜单DTO", description = "查询菜单DTO")
public class QueryMenuRequest extends BaseRequestParam {

    @NotNull(message = "菜单ID不能为空")
    @Schema(description = "菜单ID")
    private Long id;

    @Override
    public void validate() {

    }
}
