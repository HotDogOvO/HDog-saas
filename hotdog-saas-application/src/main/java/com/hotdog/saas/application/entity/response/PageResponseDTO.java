package com.hotdog.saas.application.entity.response;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageResponseDTO<T> {

    private Long total;

    private Long pageIndex;

    private Long pageSize;

    private List<T> data;

}
