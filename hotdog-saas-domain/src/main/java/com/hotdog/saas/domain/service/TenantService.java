package com.hotdog.saas.domain.service;

import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface TenantService {

    /**
     * 创建租户
     *
     * @param tenant tenant
     * @return 是否成功
     */
    Boolean createTenant(Tenant tenant);

    /**
     * 查询租户列表
     *
     * @param tenant      查询条件
     * @param pageRequest 分页条件
     * @return 租户分页列表
     */
    PageResponse<List<Tenant>> getTenantList(Tenant tenant, PageRequest pageRequest);

    /**
     * 判断租户是否存在
     *
     * @param name  租户名
     * @param appId 租户appId
     */
    void existTenant(String name, String appId);

}
