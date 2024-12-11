package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.TenantRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.TenantConverter;
import com.hotdog.saas.infra.dao.TenantMapper;
import com.hotdog.saas.infra.entity.po.TenantPO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TenantRepositoryImpl extends AbstractBaseRepository implements TenantRepository {

    private final TenantMapper tenantMapper;

    public TenantRepositoryImpl(TenantMapper tenantMapper) {
        this.tenantMapper = tenantMapper;
    }

    @Override
    public Integer save(Tenant tenant) {
        TenantPO tenantPO = TenantConverter.INSTANCE.convert2PO(tenant);
        LocalDateTime now = DateUtils.now();
        tenantPO.setCreator(tenant.getOperator()).setCreateTime(now)
                .setUpdater(tenant.getOperator()).setUpdateTime(now);
        return tenantMapper.insert(tenantPO);
    }

    @Override
    public PageResponse<List<Tenant>> listPage(Tenant tenant, PageRequest pageRequest) {
        Page<TenantPO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<TenantPO> queryWrapper = new LambdaQueryWrapper<>();
        Page<TenantPO> pageResult = tenantMapper.selectPage(page, queryWrapper);
        PageResponse<List<Tenant>> listPageResponse = pageConverter(pageResult);
        List<Tenant> list = pageResult.getRecords().stream().map(TenantConverter.INSTANCE::convert).toList();
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public Long count(Tenant tenant) {
        LambdaQueryWrapper<TenantPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantPO::getDeleted, DeleteEnum.NO.getCode())
                .eq(StringUtils.isNotEmpty(tenant.getName()), TenantPO::getName, tenant.getName())
                .eq(StringUtils.isNotEmpty(tenant.getAppId()), TenantPO::getAppId, tenant.getAppId());
        return tenantMapper.selectCount(queryWrapper);
    }

}
