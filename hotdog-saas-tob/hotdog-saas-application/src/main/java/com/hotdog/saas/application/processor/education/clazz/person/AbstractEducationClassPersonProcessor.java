package com.hotdog.saas.application.processor.education.clazz.person;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.education.clazz.AbstractEducationClassProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractEducationClassPersonProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractEducationClassProcessor<Req, Resp> implements BizProcessorTemplate<Req, Resp> {

}
