package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.assembler.TenantAssembler;
import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.utils.SignUtils;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantCreateProcess extends AbstractTenantProcessor<CreateTenantRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(CreateTenantRequest request, BaseResponse<Boolean> response) {
        int flag = tenantRepository.save(buildTenant(request));
        response.setData(flag > 0);
    }

    private Tenant buildTenant(CreateTenantRequest createTenantRequest) {
        Tenant tenant = TenantAssembler.INSTANCE.convert(createTenantRequest);
        tenant.setAppSecret(SignUtils.generatorAppSecret());
        return tenant;
    }

}
