package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface TenantRepository {

    Integer save(Tenant tenant);

    PageResponse<List<Tenant>> listPage(Tenant tenant, PageRequest pageRequest);

    Tenant findById(Long id);

    Long exists(Long id);

    Long existsByName(String name);

    Long existsByAppId(String appId);

    Integer modify(Tenant tenant);

    Integer remove(Long id, String operator);
}
