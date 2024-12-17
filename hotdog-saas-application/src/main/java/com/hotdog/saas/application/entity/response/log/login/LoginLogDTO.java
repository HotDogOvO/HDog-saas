package com.hotdog.saas.application.entity.response.log.login;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "登陆日志返回DTO", description = "登陆日志返回DTO")
public class LoginLogDTO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "日志类型")
    private Integer logType;

    @Schema(description = "登录IP")
    private String loginIp;

    @Schema(description = "登录时间")
    private LocalDateTime loginDate;

    @Schema(description = "UserAgent")
    private String userAgent;

    @Schema(description = "是否成功（0：成功，1：失败）")
    private Integer success;

    @Schema(description = "失败原因")
    private String errorMessage;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
