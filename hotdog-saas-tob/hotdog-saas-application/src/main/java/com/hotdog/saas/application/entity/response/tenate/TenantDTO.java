package com.hotdog.saas.application.entity.response.tenate;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "租户返回DTO", description = "租户返回DTO")
public class TenantDTO {

    @Schema(description = "租户ID")
    private Long id;

    @Schema(description = "租户名")
    private String name;

    @Schema(description = "联系人姓名")
    private String contactName;

    @Schema(description = "联系人手机号")
    private String contractPhone;

    @Schema(description = "联系人邮箱")
    private String contractEmail;

    @Schema(description = "appId")
    private String appId;

    @Schema(description = "过期时间")
    private LocalDateTime expireTime;

    @Schema(description = "状态（0正常 1停用）")
    private Integer status;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
