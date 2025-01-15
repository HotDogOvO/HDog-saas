package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.assembler.EducationCourseAttachAssembler;
import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.request.education.attach.EducationCourseAttachRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseAttachDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.enums.education.CourseAttachTypeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.foundation.FileService;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseAttach;
import com.hotdog.saas.domain.model.EducationCourseType;
import com.hotdog.saas.domain.model.EducationCourseTypeRelation;
import com.hotdog.saas.domain.model.common.FileUpload;
import com.hotdog.saas.domain.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    protected EducationCourseAttachRepository educationCourseAttachRepository;

    @Autowired
    protected EducationCourseClassRepository educationCourseClassRepository;

    @Autowired
    protected EducationCourseClassPersonRepository educationCourseClassPersonRepository;

    @Autowired
    protected EducationCourseClassScheduleRepository educationCourseClassScheduleRepository;

    @Autowired
    protected EducationCourseClassRecordRepository educationCourseClassRecordRepository;

    @Autowired
    protected EducationCourseClassTrailRepository educationCourseClassTrailRepository;

    @Autowired
    protected EducationCourseClassSignInRepository educationCourseClassSignInRepository;

    @Autowired
    protected EducationCourseClassEnrollRepository educationCourseClassEnrollRepository;

    @Autowired
    protected FileService fileService;

    @Autowired
    protected RedisCacheService redisCacheService;

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
    protected void existsByCourseNo(String courseNo) {
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
     * @param educationCourseTypeMap 课程分类Map
     * @return
     */
    protected EducationCourseDTO convertEducationCourseDTO(EducationCourse educationCourse, Map<Long, String> educationCourseTypeMap) {
        EducationCourseDTO educationCourseDTO = EducationCourseAssembler.INSTANCE.convertToDTO(educationCourse);
        // 课程分类
        String courseNo = educationCourse.getCourseNo();
        EducationCourseTypeRelation educationCourseTypeRelation = educationCourseTypeRelationRepository.findByCourseNo(courseNo);

        // 课程分类转换
        if (Objects.nonNull(educationCourseTypeRelation)) {
            Long typeId = educationCourseTypeRelation.getTypeId();
            educationCourseDTO.setCourseTypeId(typeId);
            educationCourseDTO.setCourseTypeName(educationCourseTypeMap.get(typeId));
        }
        List<EducationCourseAttach> attachList = educationCourseAttachRepository.findByCourseNo(courseNo);
        if(!CollectionUtils.isEmpty(attachList)){
            List<EducationCourseAttachDTO> educationCourseAttachDTOList = EducationCourseAttachAssembler.INSTANCE.convert2DTOList(attachList);
            // 路径转换
            educationCourseAttachDTOList = educationCourseAttachDTOList.stream().peek(attach -> attach.setAttachUrl(fileService.downloadFile(attach.getAttachUrl()))).toList();
            educationCourseDTO.setAttachList(educationCourseAttachDTOList);
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

    protected EducationCourseAttach uploadAttach(EducationCourseAttachRequest attachRequest, String courseNo, String operator){
        FileUpload fileUpload = fileService.uploadFormal(attachRequest.getAttachUrl());

        return EducationCourseAttach.builder()
                .courseNo(courseNo)
                .attachUrl(fileUpload.getFilePath())
                .attachType(attachRequest.getAttachType())
                .operator(operator)
                .build();
    }

    /**
     * 获取课程分类Map
     * <id, name>
     * @param wechatId 微信ID
     * @return
     */
    protected Map<Long, String> getCourseTypeMap(Long wechatId){
        EducationCourseType educationCourseType = EducationCourseType.builder().wechatId(wechatId).build();

        return educationCourseTypeRepository.list(educationCourseType).stream()
                .collect(Collectors.toMap(EducationCourseType::getId, EducationCourseType::getName));
    }

}
