package com.hotdog.saas.application.entity.response.wechat.app;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WechatAppDTO {

    @Schema(description = "小程序ID")
    private Long id;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "小程序名称")
    private String name;

    @Schema(description = "小程序原始ID")
    private String wechatSign;

    @Schema(description = "小程序AppID")
    private String wechatAppId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
