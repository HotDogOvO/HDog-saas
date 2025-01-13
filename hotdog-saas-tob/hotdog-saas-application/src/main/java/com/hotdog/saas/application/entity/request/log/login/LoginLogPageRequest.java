package com.hotdog.saas.application.entity.request.log.login;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "登陆日志分页DTO", description = "登陆日志分页DTO")
public class LoginLogPageRequest extends PageRequestParam {

    @NotNull(message = "租户ID不能为空")
    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "是否成功（0：成功，1：失败）")
    private Integer success;

}
