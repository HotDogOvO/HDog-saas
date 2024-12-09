package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public abstract class AbstractBaseRepository {

    protected <PR, Resp> PageResponse<List<Resp>> pageConverter(Page<PR> page) {
        PageResponse<List<Resp>> pageResponse = new PageResponse<>();
        pageResponse.setTotal(page.getTotal())
                .setPageIndex(page.getCurrent())
                .setPageSize(page.getSize());
        return pageResponse;
    }
}
