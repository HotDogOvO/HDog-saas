package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.domain.model.Tenant;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TenantAssembler {

    TenantAssembler INSTANCE = Mappers.getMapper(TenantAssembler.class);

    Tenant convert(CreateTenantRequest createTenantRequest);

    TenantDTO convert(Tenant tenant);
}
