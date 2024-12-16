package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.assembler.TenantAssembler;
import com.hotdog.saas.application.entity.request.tenate.QueryTenantRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Tenant;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantDetailProcessor extends AbstractTenantProcessor<QueryTenantRequest, BaseResponse<TenantDTO>> {

    @Override
    public BaseResponse<TenantDTO> initResult() {
        BaseResponse<TenantDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(QueryTenantRequest queryTenantRequest, BaseResponse<TenantDTO> response) {
        Long tenantId = queryTenantRequest.getId();
        super.exists(tenantId);
        Tenant tenant = tenantRepository.findById(tenantId);
        TenantDTO tenantDTO = TenantAssembler.INSTANCE.convertToDTO(tenant);
        response.setData(tenantDTO);
    }

}
