package com.hotdog.saas.application.processor.common;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.foundation.FileService;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractFileUploadProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcessor implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    protected FileService fileService;

}
