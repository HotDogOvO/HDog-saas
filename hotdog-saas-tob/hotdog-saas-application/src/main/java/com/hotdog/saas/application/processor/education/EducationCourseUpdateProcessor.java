package com.hotdog.saas.application.processor.education;

import com.google.common.collect.Lists;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.entity.request.education.UpdateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.attach.EducationCourseAttachRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseAttach;
import com.hotdog.saas.domain.model.EducationCourseTypeRelation;
import com.hotdog.saas.domain.utils.SystemUtils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseUpdateProcessor extends AbstractEducationProcessor<UpdateEducationCourseRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(UpdateEducationCourseRequest request, BaseResponse<Boolean> response) {
        String courseNo = request.getCourseNo();
        super.existsByCourseNo(courseNo);

        // 1. 尝试修改课程类型
        super.modifyCourseType(courseNo, request.getCourseTypeId());

        // 2. 尝试修改课程附件
        modifyCourseAttach(request.getAttachList(), courseNo, request.getOperator());

        // 3. 修改课程
        EducationCourse educationCourse = EducationCourseAssembler.INSTANCE.convert(request);
        educationCourse.setTenantId(getTenantId());
        Integer modifyFlag = educationCourseRepository.modify(educationCourse);

        response.setData(checkFlag(modifyFlag));
    }

    private void modifyCourseAttach(List<EducationCourseAttachRequest> requestAttachList, String courseNo, String operator) {
        // 转换请求和现有附件列表为 Map
        Map<String, EducationCourseAttachRequest> requestAttachMap = requestAttachList.stream()
                .collect(Collectors.toMap(EducationCourseAttachRequest::getAttachUrl, Function.identity()));
        Map<String, EducationCourseAttach> sourceAttachMap = educationCourseAttachRepository.findByCourseNo(courseNo).stream()
                .collect(Collectors.toMap(EducationCourseAttach::getAttachUrl, Function.identity()));

        // 计算需要上传和删除的附件 URL
        Set<String> needUploadAttachUrl = SystemUtils.difference(requestAttachMap.keySet(), sourceAttachMap.keySet());
        Set<String> needDeleteAttachUrl = SystemUtils.difference(sourceAttachMap.keySet(), requestAttachMap.keySet());

        // 重新上传文件
        if (!needUploadAttachUrl.isEmpty()) {
            List<EducationCourseAttach> createAttachList = needUploadAttachUrl.stream()
                    .map(url -> uploadAttach(requestAttachMap.get(url), courseNo, operator))
                    .toList();
            educationCourseAttachRepository.batchSave(createAttachList);
        }

        // 删除文件
        if (!needDeleteAttachUrl.isEmpty()) {
            List<Long> deleteIdList = needDeleteAttachUrl.stream()
                    .peek(fileService::deleteFile)
                    .map(url -> sourceAttachMap.get(url).getId())
                    .toList();
            educationCourseAttachRepository.batchRemove(deleteIdList, operator);
        }
    }

}
