package com.hotdog.saas.application.entity.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "全局公用返回值", description = "全局公用返回值")
public class BaseResponseDTO {

    @Schema(description = "状态（0正常 1停用）")
    private Boolean status;

    @Schema(description = "删除状态（0正常 1停用）")
    private Boolean deleted;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
