package com.hotdog.saas.domain.service.impl;

import com.hotdog.saas.domain.service.TenantService;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.TenantRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createTenant(Tenant tenant) {
        int flag = tenantRepository.save(tenant);
        return flag > 0;
    }

    @Override
    public PageResponse<List<Tenant>> getTenantList(Tenant tenant, PageRequest pageRequest) {
        return tenantRepository.listPage(tenant, pageRequest);
    }

    @Override
    public void existTenant(String name, String appId) {
        // 租户名校验
        Long nameCount = tenantRepository.count(Tenant.builder().name(name).build());
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "创建租户失败，租户名重复");
        }

        // appId校验
        Long appIdCount = tenantRepository.count(Tenant.builder().appId(appId).build());
        if (appIdCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "创建租户失败，appId重复");
        }
    }
}
