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
import com.hotdog.saas.infra.entity.TenantDO;

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
        TenantDO tenantDO = TenantConverter.INSTANCE.convert2DO(tenant);
        LocalDateTime now = DateUtils.now();
        tenantDO.setCreator(tenant.getOperator()).setCreateTime(now)
                .setUpdater(tenant.getOperator()).setUpdateTime(now);
        return tenantMapper.insert(tenantDO);
    }

    @Override
    public PageResponse<List<Tenant>> listPage(Tenant tenant, PageRequest pageRequest) {
        Page<TenantDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<TenantDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(TenantDO::getCreateTime);

        Page<TenantDO> pageResult = tenantMapper.selectPage(page, queryWrapper);
        List<Tenant> list = pageResult.getRecords().stream().map(TenantConverter.INSTANCE::convert).toList();

        PageResponse<List<Tenant>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public Tenant findById(Long id) {
        TenantDO tenantDO = tenantMapper.selectById(id);
        return TenantConverter.INSTANCE.convert(tenantDO);
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<TenantDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(TenantDO::getId, id);
        return tenantMapper.selectCount(queryWrapper);
    }

    @Override
    public Long existsByName(String name) {
        LambdaQueryWrapper<TenantDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(TenantDO::getName, name);
        return tenantMapper.selectCount(queryWrapper);
    }

    @Override
    public Long existsByAppId(String appId) {
        LambdaQueryWrapper<TenantDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(TenantDO::getAppId, appId);
        return tenantMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(Tenant tenant) {
        TenantDO tenantDO = TenantConverter.INSTANCE.convert2DO(tenant)
                .setUpdater(tenant.getOperator())
                .setUpdateTime(DateUtils.now());
        return tenantMapper.updateById(tenantDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        TenantDO tenantDO = new TenantDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return tenantMapper.updateById(tenantDO);
    }

}
