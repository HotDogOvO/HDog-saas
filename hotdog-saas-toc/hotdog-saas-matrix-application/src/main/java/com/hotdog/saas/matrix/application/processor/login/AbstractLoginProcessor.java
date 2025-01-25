package com.hotdog.saas.matrix.application.processor.login;

import com.hotdog.saas.matrix.application.entity.request.BaseRequestParam;
import com.hotdog.saas.matrix.application.entity.response.BaseResponse;
import com.hotdog.saas.matrix.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.matrix.application.template.BizProcessorTemplate;

public abstract class AbstractLoginProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcessor implements BizProcessorTemplate<Req, Resp> {


}
