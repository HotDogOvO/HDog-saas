package com.hotdog.saas.application.entity.response;

import com.google.common.collect.Lists;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(name = "分页统一返回值", description = "分页统一返回值")
public class PageResponseDTO<T> {

    @Schema(description = "总条数")
    private Integer total;

    @Schema(description = "当前页数")
    private Integer pageIndex;

    @Schema(description = "分页条数")
    private Integer pageSize;

    @Schema(description = "具体数据")
    private List<T> data;

    public void initPageResponse(){
        this.total = 0;
        this.pageIndex = 1;
        this.pageSize = 20;
        this.data = Lists.newArrayList();
    }

}
