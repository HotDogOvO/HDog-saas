package com.hotdog.saas.application.processor.tenant;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.service.TenantService;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.application.entity.response.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTenantProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    protected TenantService tenantService;

}
