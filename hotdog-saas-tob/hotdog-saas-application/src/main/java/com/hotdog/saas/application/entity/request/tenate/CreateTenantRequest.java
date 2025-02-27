package com.hotdog.saas.application.entity.request.tenate;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.constant.Constants;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "创建租户DTO", description = "创建租户DTO")
public class CreateTenantRequest extends BaseRequestParam {

    @NotBlank(message = "租户名不能为空")
    @Schema(description = "租户名")
    private String name;

    @NotBlank(message = "联系人不能为空")
    @Schema(description = "租户联系人姓名")
    private String contactName;

    @Pattern(regexp = Constants.PHONE_REGULAR_EXPRESSION, message = "手机号格式不正确")
    @Schema(description = "租户联系人手机号")
    private String contractPhone;

    @Email(message = "邮箱格式不正确")
    @Schema(description = "租户联系人邮箱")
    private String contractEmail;

    @NotBlank(message = "appId不能为空")
    @Schema(description = "租户AppId")
    private String appId;

    @NotNull(message = "过期时间不能为空")
    @Schema(description = "租户过期时间")
    private LocalDateTime expireTime;

    @Override
    public void validate() {

    }
}
