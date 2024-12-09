package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.infra.entity.po.TenantPO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TenantConverter {

    TenantConverter INSTANCE = Mappers.getMapper(TenantConverter.class);

    TenantPO convert2PO(Tenant tenant);

    Tenant convert(TenantPO tenantPO);
}
