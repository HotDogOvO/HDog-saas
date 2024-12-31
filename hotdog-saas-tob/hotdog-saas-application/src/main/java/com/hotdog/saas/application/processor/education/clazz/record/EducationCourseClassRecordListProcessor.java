package com.hotdog.saas.application.processor.education.clazz.record;

import com.hotdog.saas.application.assembler.EducationCourseClassRecordAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.record.EducationCourseClassRecordPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassRecordDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassRecord;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassRecordListProcessor extends AbstractEducationClassRecordProcessor<EducationCourseClassRecordPageRequest, BaseResponse<PageResponseDTO<EducationCourseClassRecordDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassRecordDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseClassRecordDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassRecordPageRequest request, BaseResponse<PageResponseDTO<EducationCourseClassRecordDTO>> response) {
        request.initPage();
        EducationCourseClassRecord educationCourseClassRecord = EducationCourseClassRecordAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseClassRecord>> listPageResponse = educationCourseClassRecordRepository.listPage(educationCourseClassRecord, pageRequest);

        PageResponseDTO<EducationCourseClassRecordDTO> list = EducationCourseClassRecordAssembler.INSTANCE.convertPage(listPageResponse);

        response.setData(list);
    }

}
