package com.hotdog.saas.application.entity.request.wechat.app;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "创建小程序DTO", description = "创建小程序DTO")
public class CreateWechatAppRequest extends BaseRequestParam {

    @NotNull(message = "租户ID不能为空")
    @Schema(description = "租户ID")
    private Long tenantId;

    @NotBlank(message = "小程序名称不能为空")
    @Schema(description = "小程序名称")
    private String name;

    @NotBlank(message = "小程序原始ID不能为空")
    @Schema(description = "小程序原始ID")
    private String wechatSign;

    @NotBlank(message = "小程序AppID不能为空")
    @Schema(description = "小程序AppID")
    private String wechatAppId;

    @NotBlank(message = "小程序AppSecret不能为空")
    @Schema(description = "小程序AppSecret")
    private String wechatAppSecret;

    @NotBlank(message = "业务类型不能为空")
    @Schema(description = "业务类型")
    private Integer businessType;

    @Schema(description = "备注")
    private String remark;

    @Override
    public void validate() {

    }
}
