package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.domain.repository.EducationCourseRepository;
import com.hotdog.saas.domain.repository.RoleRepository;
import com.hotdog.saas.domain.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import io.micrometer.common.util.StringUtils;

public abstract class AbstractEducationProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcessor implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    protected EducationCourseRepository educationCourseRepository;

    protected void existsByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        Long nameCount = educationCourseRepository.existsByName(name, getTenantId());
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "课程名重复");
        }
    }

    protected void exists(String courseNo) {
        if (Objects.isNull(courseNo)) {
            return;
        }
        Long count = educationCourseRepository.exists(courseNo, getTenantId());
        if (count == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "课程不存在");
        }
    }

}
