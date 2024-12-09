package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;

import java.util.List;

public interface TenantFacade {

    BaseResponse<Boolean> createTenant(CreateTenantRequest createTenantRequest);

    BaseResponse<PageResponseDTO<TenantDTO>> getTenantList(TenantPageRequest tenantPageRequest);
}
