package com.hotdog.saas.application.entity.request;

import lombok.Data;

@Data
public class PageRequestParam extends BaseRequestParam{

    private Integer pageIndex;

    private Integer pageSize;

    @Override
    public void validate() {

    }
}
