package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.assembler.TenantAssembler;
import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.DeleteTenantRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.config.ProjectConfig;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class TenantDeleteProcess extends AbstractTenantProcessor<DeleteTenantRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteTenantRequest request, BaseResponse<Boolean> response) {
        // 校验租户是否存在
        super.exists(request.getId());

        Integer removeFlag = tenantRepository.remove(request.getId(), request.getOperator());
        response.setData(checkFlag(removeFlag));
    }

}