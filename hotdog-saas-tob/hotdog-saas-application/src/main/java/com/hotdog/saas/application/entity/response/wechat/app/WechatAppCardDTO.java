package com.hotdog.saas.application.entity.response.wechat.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "小程序卡片返回DTO", description = "小程序卡片返回DTO")
public class WechatAppCardDTO {

    @Schema(description = "小程序ID")
    private Long id;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "小程序名称")
    private String name;

    @Schema(description = "业务类型")
    private Integer businessType;

    @Schema(description = "备注")
    private String remark;
}
