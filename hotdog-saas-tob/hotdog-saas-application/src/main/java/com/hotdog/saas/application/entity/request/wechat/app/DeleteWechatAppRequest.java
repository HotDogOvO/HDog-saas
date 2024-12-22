package com.hotdog.saas.application.entity.request.wechat.app;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "删除小程序DTO", description = "删除小程序DTO")
public class DeleteWechatAppRequest extends BaseRequestParam {

    @NotNull(message = "小程序ID不能为空")
    @Schema(description = "小程序ID")
    private Long id;

    @Override
    public void validate() {

    }
}
