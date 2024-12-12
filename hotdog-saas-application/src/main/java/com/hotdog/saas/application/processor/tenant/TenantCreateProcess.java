package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.assembler.TenantAssembler;
import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.config.ProjectConfig;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.utils.SignUtils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateTenantRequest request, BaseResponse<Boolean> response) {
        // 校验租户名是否存在
        super.existsByName(request.getName());
        // 校验AppId是否存在
        super.existsByAppId(request.getAppId());
        // 保存
        Integer saveFlag = tenantRepository.save(buildTenant(request));
        response.setData(checkFlag(saveFlag));
    }

    private Tenant buildTenant(CreateTenantRequest createTenantRequest) {
        Tenant tenant = TenantAssembler.INSTANCE.convert(createTenantRequest);
        try {
            tenant.setAppSecret(SignUtils.generatorAppSecret(ProjectConfig.appSecret));
        } catch (Exception e) {
            log.error("创建租户失败，生成appSecret异常，{}", e.getMessage(), e);
            throw new BusinessException(ResultCodeEnum.FAIL);
        }
        return tenant;
    }

}
