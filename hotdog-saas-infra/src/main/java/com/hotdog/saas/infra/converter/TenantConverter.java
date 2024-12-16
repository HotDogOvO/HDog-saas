package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.infra.entity.TenantDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TenantConverter {

    TenantConverter INSTANCE = Mappers.getMapper(TenantConverter.class);

    TenantDO convert2DO(Tenant tenant);

    Tenant convert(TenantDO tenantDO);
}
