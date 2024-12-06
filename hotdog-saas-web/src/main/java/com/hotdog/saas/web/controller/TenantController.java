package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.application.facade.TenantFacade;
import com.hotdog.saas.application.entity.response.common.BaseResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotdog/v1/tenant")
public class TenantController {

    private final TenantFacade tenantFacade;

    public TenantController(TenantFacade tenantFacade) {
        this.tenantFacade = tenantFacade;
    }

    @PostMapping("/list/page")
    public BaseResponse<List<TenantDTO>> tenantListPage(@RequestBody TenantPageRequest tenantPageRequest){
        return tenantFacade.getTenantList(tenantPageRequest);
    }

    @PostMapping("/create")
    public BaseResponse<Boolean> createTenant(@RequestBody @Validated CreateTenantRequest createTenantRequest){
        return tenantFacade.createTenant(createTenantRequest);
    }

}
