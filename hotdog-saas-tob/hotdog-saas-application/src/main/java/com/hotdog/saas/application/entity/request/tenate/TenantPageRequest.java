package com.hotdog.saas.application.entity.request.tenate;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "租户分页DTO", description = "租户分页DTO")
public class TenantPageRequest extends PageRequestParam {

    @Schema(description = "租户名")
    private String name;

    @Schema(description = "联系人姓名")
    private String contactName;

    @Schema(description = "联系人手机号")
    private String contractPhone;

    @Schema(description = "状态（0正常 1停用）")
    private Integer status;

}
