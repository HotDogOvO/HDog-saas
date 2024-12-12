package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.DeleteTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.QueryTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.request.tenate.UpdateTenantRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.application.facade.TenantFacade;
import com.hotdog.saas.application.processor.tenant.TenantCreateProcess;
import com.hotdog.saas.application.processor.tenant.TenantDeleteProcess;
import com.hotdog.saas.application.processor.tenant.TenantDetailProcess;
import com.hotdog.saas.application.processor.tenant.TenantListProcess;
import com.hotdog.saas.application.processor.BaseProcess;
import com.hotdog.saas.application.entity.response.BaseResponse;

import com.hotdog.saas.application.processor.tenant.TenantUpdateProcess;
import org.springframework.stereotype.Component;

@Component
public class TenantFacadeImpl extends BaseProcess implements TenantFacade {

    private final TenantCreateProcess tenantCreateProcess;
    private final TenantListProcess tenantListProcess;
    private final TenantDetailProcess tenantDetailProcess;
    private final TenantUpdateProcess tenantUpdateProcess;
    private final TenantDeleteProcess tenantDeleteProcess;

    public TenantFacadeImpl(TenantCreateProcess tenantCreateProcess, TenantListProcess tenantListProcess, TenantDetailProcess tenantDetailProcess, TenantUpdateProcess tenantUpdateProcess, TenantDeleteProcess tenantDeleteProcess) {
        this.tenantCreateProcess = tenantCreateProcess;
        this.tenantListProcess = tenantListProcess;
        this.tenantDetailProcess = tenantDetailProcess;
        this.tenantUpdateProcess = tenantUpdateProcess;
        this.tenantDeleteProcess = tenantDeleteProcess;
    }

    @Override
    public BaseResponse<Boolean> createTenant(CreateTenantRequest createTenantRequest) {
        return this.doBiz(createTenantRequest, tenantCreateProcess, false, () -> "");
    }

    @Override
    public BaseResponse<PageResponseDTO<TenantDTO>> tenantListPage(TenantPageRequest tenantPageRequest) {
        return this.doBiz(tenantPageRequest, tenantListProcess, false, () -> "");
    }

    @Override
    public BaseResponse<TenantDTO> tenantDetail(QueryTenantRequest queryTenantRequest) {
        return this.doBiz(queryTenantRequest, tenantDetailProcess, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> updateTenant(UpdateTenantRequest updateTenantRequest) {
        return this.doBiz(updateTenantRequest, tenantUpdateProcess, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> deleteTenant(DeleteTenantRequest deleteTenantRequest) {
        return this.doBiz(deleteTenantRequest, tenantDeleteProcess, false, () -> "");
    }

}
