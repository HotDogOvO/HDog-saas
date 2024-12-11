package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.application.facade.TenantFacade;
import com.hotdog.saas.application.processor.tenant.TenantCreateProcess;
import com.hotdog.saas.application.processor.tenant.TenantListProcess;
import com.hotdog.saas.application.processor.BaseProcess;
import com.hotdog.saas.application.entity.response.BaseResponse;

import org.springframework.stereotype.Component;

@Component
public class TenantFacadeImpl extends BaseProcess implements TenantFacade {

    private final TenantCreateProcess tenantCreateProcess;
    private final TenantListProcess tenantListProcess;

    public TenantFacadeImpl(TenantCreateProcess tenantCreateProcess, TenantListProcess tenantListProcess) {
        this.tenantCreateProcess = tenantCreateProcess;
        this.tenantListProcess = tenantListProcess;
    }

    @Override
    public BaseResponse<Boolean> createTenant(CreateTenantRequest createTenantRequest) {
        return this.doBiz(createTenantRequest, tenantCreateProcess, false, () -> "");
    }

    @Override
    public BaseResponse<PageResponseDTO<TenantDTO>> getTenantList(TenantPageRequest tenantPageRequest) {
        return this.doBiz(tenantPageRequest, tenantListProcess, false, () -> "");
    }

}
