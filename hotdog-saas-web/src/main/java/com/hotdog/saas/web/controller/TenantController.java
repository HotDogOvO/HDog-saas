package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.application.facade.TenantFacade;
import com.hotdog.saas.application.entity.response.BaseResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "租户管理")
@RestController
@RequestMapping("/api/hotdog/v1/tenant")
public class TenantController {

    private final TenantFacade tenantFacade;

    public TenantController(TenantFacade tenantFacade) {
        this.tenantFacade = tenantFacade;
    }

    @Operation(summary = "查询租户分页列表")
    @Parameters({
            @Parameter(name = "pageIndex", description = "页数", required = true),
            @Parameter(name = "pageSize", description = "单页条数", required = true),
    })
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<TenantDTO>> tenantListPage(@RequestBody TenantPageRequest tenantPageRequest) {
        return tenantFacade.getTenantList(tenantPageRequest);
    }

    @Operation(summary = "创建租户")
    @PostMapping("/create")
    public BaseResponse<Boolean> createTenant(@RequestBody @Validated CreateTenantRequest createTenantRequest) {
        return tenantFacade.createTenant(createTenantRequest);
    }

}
