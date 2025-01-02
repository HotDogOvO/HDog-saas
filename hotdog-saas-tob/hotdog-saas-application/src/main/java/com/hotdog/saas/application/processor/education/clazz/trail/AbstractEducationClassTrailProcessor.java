package com.hotdog.saas.application.processor.education.clazz.trail;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.education.clazz.AbstractEducationClassProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import java.util.Objects;

public abstract class AbstractEducationClassTrailProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractEducationClassProcessor<Req, Resp> implements BizProcessorTemplate<Req, Resp> {

    protected void existsByClassTrailId(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        Long nameCount = educationCourseClassTrailRepository.exists(id);
        if (nameCount == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "试课记录不存在");
        }
    }

}
