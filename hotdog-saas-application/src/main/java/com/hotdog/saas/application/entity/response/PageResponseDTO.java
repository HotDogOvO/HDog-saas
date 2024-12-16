package com.hotdog.saas.application.entity.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(name = "分页统一返回值", description = "分页统一返回值")
public class PageResponseDTO<T> {

    @Schema(description = "总条数")
    private Long total;

    @Schema(description = "当前页数")
    private Long pageIndex;

    @Schema(description = "分页条数")
    private Long pageSize;

    @Schema(description = "具体数据")
    private List<T> data;

}
