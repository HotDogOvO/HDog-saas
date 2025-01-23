package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseTypeRelation;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseListProcessor extends AbstractEducationProcessor<EducationCoursePageRequest, BaseResponse<PageResponseDTO<EducationCourseDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCoursePageRequest request, BaseResponse<PageResponseDTO<EducationCourseDTO>> response) {
        request.initPage();
        EducationCourse educationCourse = EducationCourseAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        // 1. 课程分类查询
        PageResponseDTO<EducationCourseDTO> educationCoursePageResponseDTO = new PageResponseDTO<>();
        List<String> courseNoList = Lists.newArrayList();
        if(Objects.nonNull(request.getCourseTypeId())){
            courseNoList = educationCourseTypeRelationRepository.findByTypeId(request.getCourseTypeId()).stream().map(EducationCourseTypeRelation::getCourseNo).toList();
            // 没有分类数据，短路返回
            if(CollectionUtils.isEmpty(courseNoList)){
                educationCoursePageResponseDTO.initPageResponse();
                response.setData(educationCoursePageResponseDTO);
                return;
            }
        }
        educationCourse.setCourseNoList(courseNoList);

        // 2. 课程查询
        PageResponse<List<EducationCourse>> listPageResponse = educationCourseRepository.listPage(educationCourse, pageRequest);

        Map<Long, String> courseTypeMap = super.getCourseTypeMap(request.getWechatId());
        List<EducationCourseDTO> list = listPageResponse.getData().stream()
                .map(x -> super.convertEducationCourseDTO(x, courseTypeMap))
                .toList();
        educationCoursePageResponseDTO = EducationCourseAssembler.INSTANCE.convertPage(listPageResponse);

        educationCoursePageResponseDTO.setData(list);
        response.setData(educationCoursePageResponseDTO);
    }

}
