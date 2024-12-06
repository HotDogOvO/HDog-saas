package com.hotdog.saas.infra.repository;

import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.repository.TenantRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.TenantConverter;
import com.hotdog.saas.infra.dao.TenantMapper;
import com.hotdog.saas.infra.entity.po.TenantPO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TenantRepositoryImpl implements TenantRepository {

    private final TenantMapper tenantMapper;

    public TenantRepositoryImpl(TenantMapper tenantMapper) {
        this.tenantMapper = tenantMapper;
    }

    @Override
    public int save(Tenant tenant) {
        TenantPO tenantPO = TenantConverter.INSTANCE.convert2PO(tenant);
        LocalDateTime now = DateUtils.now();
        tenantPO.setCreator(tenant.getOperator()).setCreateTime(now)
                .setUpdater(tenant.getOperator()).setUpdateTime(now);
        return tenantMapper.insert(tenantPO);
    }

    @Override
    public List<Tenant> findList() {
        List<TenantPO> tenantPOList = tenantMapper.selectList(null);
        List<Tenant> tenants = tenantPOList.stream().map(TenantConverter.INSTANCE::convert).toList();
        return tenants;
    }
}
