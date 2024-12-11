package com.hotdog.saas.application.entity.response.tenate;


import com.hotdog.saas.application.entity.response.BaseResponseDTO;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "租户返回DTO", description = "租户返回DTO")
public class TenantDTO extends BaseResponseDTO {

    @Schema(description = "租户ID")
    private Integer id;

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

//    @Schema(description = "租户秘钥")
//    private String appSecret;

    /**
     * 过期时间
     */
    @Schema(description = "过期时间")
    private LocalDateTime expireTime;

}
