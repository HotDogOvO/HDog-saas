package com.hotdog.saas.application.entity.request;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "全局请求抽象类", description = "全局请求抽象类")
public abstract class BaseRequestParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "租户AppId")
    private String appId;

    @Schema(description = "操作人")
    private String operator;

    public abstract void validate();
}
