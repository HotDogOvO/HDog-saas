package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.application.entity.response.common.BaseResponse;
import com.hotdog.saas.domain.repository.TenantRepository;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTenantProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    protected TenantRepository tenantRepository;

}
