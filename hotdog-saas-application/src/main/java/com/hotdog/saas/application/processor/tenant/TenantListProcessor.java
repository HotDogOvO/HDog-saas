package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.assembler.TenantAssembler;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantListProcessor extends AbstractTenantProcessor<TenantPageRequest, BaseResponse<PageResponseDTO<TenantDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<TenantDTO>> initResult() {
        BaseResponse<PageResponseDTO<TenantDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(TenantPageRequest tenantPageRequest, BaseResponse<PageResponseDTO<TenantDTO>> response) {
        tenantPageRequest.initPage();
        Tenant tenant = TenantAssembler.INSTANCE.convert(tenantPageRequest);
        PageRequest pageRequest = reqToPage(tenantPageRequest);

        PageResponse<List<Tenant>> listPageResponse = tenantRepository.listPage(tenant, pageRequest);

        PageResponseDTO<TenantDTO> tenantPageResponseDTO = TenantAssembler.INSTANCE.convertPage(listPageResponse);
        response.setData(tenantPageResponseDTO);
    }

}
