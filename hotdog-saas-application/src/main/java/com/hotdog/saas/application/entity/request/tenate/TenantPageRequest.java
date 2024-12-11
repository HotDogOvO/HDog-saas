package com.hotdog.saas.application.entity.request.tenate;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.request.PageRequestParam;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "租户分页请求DTO", description = "租户分页请求DTO")
public class TenantPageRequest extends PageRequestParam {

    @Override
    public void validate() {
        super.validate();
    }
}
