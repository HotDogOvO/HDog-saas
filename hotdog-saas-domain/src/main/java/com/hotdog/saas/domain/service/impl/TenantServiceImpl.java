package com.hotdog.saas.domain.service.impl;

import com.hotdog.saas.domain.service.TenantService;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.TenantRepository;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
    public void existsByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        // 租户名校验
        Long nameCount = tenantRepository.count(Tenant.builder().name(name).build());
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "租户名重复");
        }
    }

    @Override
    public void existsByAppId(String appId) {
        if (StringUtils.isEmpty(appId)) {
            return;
        }
        // appId校验
        Long appIdCount = tenantRepository.count(Tenant.builder().appId(appId).build());
        if (appIdCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "appId重复");
        }
    }

    @Override
    public void exists(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        // appId校验
        Long idCount = tenantRepository.count(Tenant.builder().id(id).build());
        if (idCount == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "租户ID不存在");
        }
    }

    @Override
    public Boolean updateTenant(Tenant tenant) {
        int flag = tenantRepository.modify(tenant);
        return flag > 0;
    }

    @Override
    public Boolean deleteTenant(Long id) {
        int flag = tenantRepository.remove(id);
        return flag > 0;
    }
}
