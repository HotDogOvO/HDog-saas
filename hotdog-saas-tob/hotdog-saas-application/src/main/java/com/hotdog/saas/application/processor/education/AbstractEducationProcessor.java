package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseType;
import com.hotdog.saas.domain.model.EducationCourseTypeRelation;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.domain.repository.EducationCourseRepository;
import com.hotdog.saas.domain.repository.EducationCourseTypeRelationRepository;
import com.hotdog.saas.domain.repository.EducationCourseTypeRepository;
import com.hotdog.saas.domain.repository.RoleRepository;
import com.hotdog.saas.domain.repository.UserRoleRepository;
import com.hotdog.saas.domain.repository.WechatAppRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import io.micrometer.common.util.StringUtils;

public abstract class AbstractEducationProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcessor implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    protected WechatAppRepository wechatAppRepository;

    @Autowired
    protected EducationCourseRepository educationCourseRepository;

    @Autowired
    protected EducationCourseTypeRepository educationCourseTypeRepository;

    @Autowired
    protected EducationCourseTypeRelationRepository educationCourseTypeRelationRepository;

    /**
     * 校验课程名是否存在
     *
     * @param name 课程名
     */
    protected void existsByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        Long nameCount = educationCourseRepository.existsByName(name, getTenantId());
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "课程名重复");
        }
    }

    /**
     * 校验课程是否存在
     *
     * @param courseNo 课程编号
     */
    protected void existsCourseNo(String courseNo) {
        if (StringUtils.isEmpty(courseNo)) {
            return;
        }
        Long count = educationCourseRepository.exists(courseNo, getTenantId());
        if (count == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "课程不存在");
        }
    }

    /**
     * 校验微信小程序是否存在
     *
     * @param wechatId 小程序ID
     */
    protected void existsByWechatId(Long wechatId) {
        if (Objects.isNull(wechatId)) {
            return;
        }
        Long count = wechatAppRepository.exists(wechatId);
        if (count == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "微信小程序不存在");
        }
    }

    /**
     * 校验课程分类是否存在
     *
     * @param courseTypeId 课程分类ID
     */
    protected void existsByCourseTypeId(Long courseTypeId) {
        if (Objects.isNull(courseTypeId)) {
            return;
        }
        Long nameCount = educationCourseTypeRepository.exists(courseTypeId);
        if (nameCount == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "课程分类不存在");
        }
    }

    /**
     * 构建课程DTO
     *
     * @param educationCourse 课程领域对象
     * @return
     */
    protected EducationCourseDTO convertEducationCourseDTO(EducationCourse educationCourse) {
        EducationCourseDTO educationCourseDTO = EducationCourseAssembler.INSTANCE.convertToDTO(educationCourse);
        EducationCourseTypeRelation educationCourseTypeRelation = educationCourseTypeRelationRepository.findByCourseNo(educationCourse.getCourseNo());
        if (Objects.nonNull(educationCourseTypeRelation)) {
            EducationCourseType educationCourseType = educationCourseTypeRepository.findById(educationCourseTypeRelation.getTypeId());
            educationCourseDTO.setCourseTypeId(educationCourseType.getId());
            educationCourseDTO.setCourseTypeName(educationCourseType.getName());
        }
        return educationCourseDTO;
    }

    /**
     * 修改课程分类（先删后增）
     *
     * @param courseNo     课程编号
     * @param courseTypeId 课程分类ID
     */
    protected void modifyCourseType(String courseNo, Long courseTypeId) {
        EducationCourseTypeRelation educationCourseTypeRelation = educationCourseTypeRelationRepository.findByCourseNo(courseNo);

        // 修改了课程类型
        if (Objects.nonNull(educationCourseTypeRelation) && !Objects.equals(educationCourseTypeRelation.getTypeId(), courseTypeId)) {
            educationCourseTypeRelationRepository.removeByCourseNo(courseNo);
            educationCourseTypeRelation.setTypeId(courseTypeId);
            educationCourseTypeRelationRepository.save(educationCourseTypeRelation);
        }

    }

}
