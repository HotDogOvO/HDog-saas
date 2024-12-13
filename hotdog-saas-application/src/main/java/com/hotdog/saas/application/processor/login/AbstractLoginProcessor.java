package com.hotdog.saas.application.processor.login;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.cache.RedisCacheService;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.repository.UserRepository;
import com.hotdog.saas.domain.utils.SignUtils;

import org.springframework.beans.factory.annotation.Autowired;

import io.micrometer.common.util.StringUtils;

public abstract class AbstractLoginProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcessor implements BizProcessorTemplate<Req, Resp> {


}
