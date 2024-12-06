package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.Tenant;

import java.util.List;

public interface TenantRepository {

    int save(Tenant tenant);

    List<Tenant> findList();
}
