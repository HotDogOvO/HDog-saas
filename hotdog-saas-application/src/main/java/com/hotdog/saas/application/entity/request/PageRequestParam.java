package com.hotdog.saas.application.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "分页请求DTO", description = "分页请求类")
public class PageRequestParam extends BaseRequestParam {

    @Schema(description = "页数")
    @NotNull(message = "页数不能为空")
    private Integer pageIndex;

    @Schema(description = "分页条数")
    @NotNull(message = "分页条数不能为空")
    private Integer pageSize;

    public void initPage(){
        if (pageIndex == null || pageIndex < 1) {
            this.pageIndex = 1;
        }

        if (pageSize == null || pageSize < 1) {
            this.pageSize = 20;
        }
    }

    @Override
    public void validate() {
    }
}
