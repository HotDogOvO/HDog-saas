package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.DeleteTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.QueryTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.request.tenate.UpdateTenantRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.application.facade.TenantFacade;
import com.hotdog.saas.application.processor.tenant.TenantCreateProcessor;
import com.hotdog.saas.application.processor.tenant.TenantDeleteProcessor;
import com.hotdog.saas.application.processor.tenant.TenantDetailProcessor;
import com.hotdog.saas.application.processor.tenant.TenantListProcessor;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.entity.response.BaseResponse;

import com.hotdog.saas.application.processor.tenant.TenantUpdateProcessor;
import org.springframework.stereotype.Component;

@Component
public class TenantFacadeImpl extends BaseProcessor implements TenantFacade {

    private final TenantCreateProcessor tenantCreateProcessor;
    private final TenantListProcessor tenantListProcessor;
    private final TenantDetailProcessor tenantDetailProcessor;
    private final TenantUpdateProcessor tenantUpdateProcessor;
    private final TenantDeleteProcessor tenantDeleteProcessor;

    public TenantFacadeImpl(TenantCreateProcessor tenantCreateProcessor, TenantListProcessor tenantListProcessor, TenantDetailProcessor tenantDetailProcessor, TenantUpdateProcessor tenantUpdateProcessor, TenantDeleteProcessor tenantDeleteProcessor) {
        this.tenantCreateProcessor = tenantCreateProcessor;
        this.tenantListProcessor = tenantListProcessor;
        this.tenantDetailProcessor = tenantDetailProcessor;
        this.tenantUpdateProcessor = tenantUpdateProcessor;
        this.tenantDeleteProcessor = tenantDeleteProcessor;
    }

    @Override
    public BaseResponse<Boolean> createTenant(CreateTenantRequest createTenantRequest) {
        return this.doBiz(createTenantRequest, tenantCreateProcessor);
    }

    @Override
    public BaseResponse<PageResponseDTO<TenantDTO>> tenantListPage(TenantPageRequest tenantPageRequest) {
        return this.doBiz(tenantPageRequest, tenantListProcessor);
    }

    @Override
    public BaseResponse<TenantDTO> tenantDetail(QueryTenantRequest queryTenantRequest) {
        return this.doBiz(queryTenantRequest, tenantDetailProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateTenant(UpdateTenantRequest updateTenantRequest) {
        return this.doBiz(updateTenantRequest, tenantUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteTenant(DeleteTenantRequest deleteTenantRequest) {
        return this.doBiz(deleteTenantRequest, tenantDeleteProcessor);
    }

}
