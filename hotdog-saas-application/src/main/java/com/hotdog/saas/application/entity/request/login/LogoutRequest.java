package com.hotdog.saas.application.entity.request.login;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.exception.BusinessException;

import org.apache.commons.lang3.StringUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "登出DTO", description = "登出DTO")
public class LogoutRequest extends BaseRequestParam {

    @Schema(description = "token")
    private String token;

    @Override
    public void validate() {
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException("token为空");
        }
    }
}
