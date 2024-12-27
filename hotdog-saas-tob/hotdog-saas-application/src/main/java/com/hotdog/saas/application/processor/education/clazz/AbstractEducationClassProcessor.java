package com.hotdog.saas.application.processor.education.clazz;

import com.hotdog.saas.application.assembler.EducationCourseClassAssembler;
import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.application.processor.education.AbstractEducationProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseClass;
import io.micrometer.common.util.StringUtils;

public abstract class AbstractEducationClassProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractEducationProcessor<Req, Resp> implements BizProcessorTemplate<Req, Resp> {

    /**
     * 校验班级名是否存在
     *
     * @param className 班级名
     * @param wechatId  微信ID
     */
    protected void existsByClassName(String className, Long wechatId) {
        if (StringUtils.isEmpty(className)) {
            return;
        }
        Long nameCount = educationCourseClassRepository.existsByName(className, wechatId);
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "班级名重复");
        }
    }

    /**
     * 校验班级是否存在
     *
     * @param classNo  班级编号
     */
    protected void existsByClassNo(String classNo) {
        if (StringUtils.isEmpty(classNo)) {
            return;
        }
        Long nameCount = educationCourseClassRepository.exists(classNo);
        if (nameCount == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "班级不存在");
        }
    }

    /**
     * 构建班级返回对象
     * @param educationCourseClass 班级领域对象
     * @return 班级返回值
     */
    protected EducationCourseClassDTO buildEducationCourseClassDTO(EducationCourseClass educationCourseClass) {
        EducationCourseClassDTO educationCourseClassDTO = EducationCourseClassAssembler.INSTANCE.convertToDTO(educationCourseClass);
        EducationCourse educationCourse = educationCourseRepository.findByCourseNo(educationCourseClass.getCourseNo());
        educationCourseClassDTO.setCourseNo(educationCourse.getCourseNo());
        educationCourseClassDTO.setCourseName(educationCourse.getName());

        return educationCourseClassDTO;
    }

}
