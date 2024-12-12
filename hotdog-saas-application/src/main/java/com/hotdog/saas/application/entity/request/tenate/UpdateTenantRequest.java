package com.hotdog.saas.application.entity.request.tenate;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String contractPhone;

    @Schema(description = "租户联系人邮箱")
    private String contractEmail;

    @Schema(description = "租户过期时间")
    private LocalDateTime expireTime;

    @Schema(description = "租户状态（0正常 1停用）")
    private Integer status;

}
