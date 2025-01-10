package com.hotdog.saas.application.entity.request.wechat.app;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "小程序分页DTO", description = "小程序分页DTO")
public class WechatAppPageRequest extends PageRequestParam {

    @NotNull(message = "租户ID不能为空")
    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "小程序名称")
    private String name;

}
