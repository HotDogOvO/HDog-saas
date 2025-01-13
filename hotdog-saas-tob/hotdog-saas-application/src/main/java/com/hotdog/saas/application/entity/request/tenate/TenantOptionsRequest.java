package com.hotdog.saas.application.entity.request.tenate;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "查询租户下拉框DTO", description = "查询租户下拉框DTO")
public class TenantOptionsRequest extends BaseRequestParam {

    @Override
    public void validate() {

    }
}
