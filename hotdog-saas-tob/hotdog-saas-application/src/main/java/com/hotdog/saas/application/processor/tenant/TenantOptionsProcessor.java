package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.assembler.TenantAssembler;
import com.hotdog.saas.application.entity.request.tenate.TenantOptionsRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantOptionsDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantOptionsProcessor extends AbstractTenantProcessor<TenantOptionsRequest, BaseResponse<List<TenantOptionsDTO>>> {

    @Override
    public BaseResponse<List<TenantOptionsDTO>> initResult() {
        BaseResponse<List<TenantOptionsDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(TenantOptionsRequest tenantOptionsRequest, BaseResponse<List<TenantOptionsDTO>> response) {
        List<Tenant> list = tenantRepository.findList(Tenant.builder().build());

        List<TenantOptionsDTO> tenantOptionsDTOList = TenantAssembler.INSTANCE.convertOptions(list);
        response.setData(tenantOptionsDTOList);
    }

}
