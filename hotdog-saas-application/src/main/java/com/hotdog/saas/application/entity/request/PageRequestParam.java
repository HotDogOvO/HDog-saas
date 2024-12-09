package com.hotdog.saas.application.entity.request;

import lombok.Data;

@Data
public class PageRequestParam extends BaseRequestParam {

    private Integer pageIndex;

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
