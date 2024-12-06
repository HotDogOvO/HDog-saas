package com.hotdog.saas.application.entity.request.tenate;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.request.PageRequestParam;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TenantPageRequest extends PageRequestParam {

    @Override
    public void validate() {
        super.validate();
    }
}
