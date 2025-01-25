package com.hotdog.saas.matrix.domain.page;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageResponse<T> {

    private Long total;

    private Long pageIndex;

    private Long pageSize;

    private T data;

}
