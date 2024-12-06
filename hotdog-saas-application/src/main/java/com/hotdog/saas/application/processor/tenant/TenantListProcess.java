package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.assembler.TenantAssembler;
import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.response.common.BaseResponse;
import com.hotdog.saas.application.entity.response.common.ResultCodeEnum;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.utils.SignUtils;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantListProcess extends AbstractTenantProcessor<TenantPageRequest, BaseResponse<List<TenantDTO>>> {

    @Override
    public BaseResponse<List<TenantDTO>> initResult() {
        BaseResponse<List<TenantDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(TenantPageRequest tenantPageRequest, BaseResponse<List<TenantDTO>> response) {
        List<Tenant> list = tenantRepository.findList();
        List<TenantDTO> result = list.stream().map(TenantAssembler.INSTANCE::convert).toList();
        response.setData(result);
    }

}
