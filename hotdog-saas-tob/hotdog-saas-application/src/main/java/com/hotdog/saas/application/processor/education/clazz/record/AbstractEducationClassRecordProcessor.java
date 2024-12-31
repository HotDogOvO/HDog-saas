package com.hotdog.saas.application.processor.education.clazz.record;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.education.clazz.AbstractEducationClassProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;

public abstract class AbstractEducationClassRecordProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractEducationClassProcessor<Req, Resp> implements BizProcessorTemplate<Req, Resp> {

}
