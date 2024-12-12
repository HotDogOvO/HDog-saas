package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.processor.AbstractBaseProcess;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.repository.TenantRepository;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.application.entity.response.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import io.micrometer.common.util.StringUtils;

public abstract class AbstractTenantProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcess implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    protected TenantRepository tenantRepository;

    /**
     * 检测租户名是否存在
     *
     * @param name 租户名
     */
    protected void existsByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        Long nameCount = tenantRepository.existsByName(name);
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "租户名重复");
        }
    }

    /**
     * 检测租户AppId是否存在
     *
     * @param appId appId
     */
    protected void existsByAppId(String appId) {
        if (StringUtils.isEmpty(appId)) {
            return;
        }
        Long appIdCount = tenantRepository.existsByAppId(appId);
        if (appIdCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "appId重复");
        }
    }

    /**
     * 检测租户是否存在
     *
     * @param id 租户ID
     */
    protected void exists(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        Long idCount = tenantRepository.exists(id);
        if (idCount == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "租户不存在");
        }
    }

}
