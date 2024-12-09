package com.hotdog.saas.application.entity.request.tenate;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTenantRequest extends BaseRequestParam {

    @NotBlank(message = "租户名不能为空")
    private String name;

    @NotBlank(message = "联系人不能为空")
    private String contactName;

    private String contractPhone;

    private String contractEmail;

    @NotBlank(message = "appId不能为空")
    private String appId;

    @NotNull(message = "过期时间不能为空")
    private LocalDateTime expireTime;

    @Override
    public void validate() {

    }
}
