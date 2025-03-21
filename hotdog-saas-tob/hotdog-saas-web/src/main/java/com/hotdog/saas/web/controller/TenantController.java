package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.DeleteTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.QueryTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantOptionsRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.request.tenate.UpdateTenantRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantOptionsDTO;
import com.hotdog.saas.application.facade.TenantFacade;
import com.hotdog.saas.application.entity.response.BaseResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "租户管理")
@RestController
@RequestMapping("/api/hotdog/v1/tenant")
public class TenantController {

    private final TenantFacade tenantFacade;

    public TenantController(TenantFacade tenantFacade) {
        this.tenantFacade = tenantFacade;
    }

    @Operation(summary = "创建租户")
    @PostMapping("/create")
    public BaseResponse<Boolean> createTenant(@RequestBody @Validated CreateTenantRequest createTenantRequest) {
        return tenantFacade.createTenant(createTenantRequest);
    }

    @Operation(summary = "查询租户分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<TenantDTO>> tenantListPage(@RequestBody @Validated TenantPageRequest tenantPageRequest) {
        return tenantFacade.tenantListPage(tenantPageRequest);
    }

    @Operation(summary = "查询租户下拉框")
    @PostMapping("/options")
    public BaseResponse<List<TenantOptionsDTO>> tenantOptions(@RequestBody TenantOptionsRequest tenantOptionsRequest) {
        return tenantFacade.tenantOptions(tenantOptionsRequest);
    }

    @Operation(summary = "查询租户详情")
    @PostMapping("/detail")
    public BaseResponse<TenantDTO> tenantDetail(@RequestBody @Validated QueryTenantRequest queryTenantRequest) {
        return tenantFacade.tenantDetail(queryTenantRequest);
    }

    @Operation(summary = "更新租户")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTenant(@RequestBody @Validated UpdateTenantRequest updateTenantRequest) {
        return tenantFacade.updateTenant(updateTenantRequest);
    }

    @Operation(summary = "删除租户")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTenant(@RequestBody @Validated DeleteTenantRequest deleteTenantRequest) {
        return tenantFacade.deleteTenant(deleteTenantRequest);
    }

}
