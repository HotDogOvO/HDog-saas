package com.hotdog.saas.application.processor.education.type;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.application.processor.education.AbstractEducationProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.repository.EducationCourseRepository;
import com.hotdog.saas.domain.repository.EducationCourseTypeRepository;
import com.hotdog.saas.domain.repository.WechatAppRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import io.micrometer.common.util.StringUtils;

public abstract class AbstractEducationTypeProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractEducationProcessor<Req, Resp> implements BizProcessorTemplate<Req, Resp> {

    protected void existsByTypeName(String name, Long wechatId) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        Long nameCount = educationCourseTypeRepository.existByTypeName(name, wechatId);
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "课程分类名重复");
        }
    }

}
