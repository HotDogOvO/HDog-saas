package com.hotdog.saas.application.processor.education.clazz.enroll;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.education.clazz.AbstractEducationClassProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import java.util.Objects;

public abstract class AbstractEducationClassEnrollProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractEducationClassProcessor<Req, Resp> implements BizProcessorTemplate<Req, Resp> {

    protected void existsByClassEnrollId(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        Long nameCount = educationCourseClassEnrollRepository.exists(id);
        if (nameCount == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "报名记录不存在");
        }
    }

}