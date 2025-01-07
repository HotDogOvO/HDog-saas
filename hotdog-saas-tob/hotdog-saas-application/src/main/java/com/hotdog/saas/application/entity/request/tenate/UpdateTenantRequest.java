package com.hotdog.saas.application.entity.request.tenate;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.constant.Constants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "更新租户DTO", description = "更新租户DTO")
public class UpdateTenantRequest extends BaseRequestParam {

    @NotNull(message = "租户ID不能为空")
    @Schema(description = "租户ID")
    private Long id;

    @Schema(description = "租户名")
    private String name;

    @Schema(description = "租户联系人姓名")
    private String contactName;

    @Schema(description = "租户联系人手机号")
    @Pattern(regexp = Constants.PHONE_REGULAR_EXPRESSION, message = "手机号格式不正确")
    private String contractPhone;

    @Schema(description = "租户联系人邮箱")
    @Email(message = "邮箱格式不正确")
    private String contractEmail;

    @Schema(description = "租户过期时间")
    private LocalDateTime expireTime;

    @Schema(description = "租户状态（0正常 1停用）")
    private Integer status;

    @Override
    public void validate() {

    }
}
