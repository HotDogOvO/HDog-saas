package com.hotdog.saas.domain.model.page;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageRequest {

    private Integer pageIndex;

    private Integer pageSize;

}
