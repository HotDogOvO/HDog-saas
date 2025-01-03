package com.hotdog.saas.application.processor.education.clazz.signin;

import com.hotdog.saas.application.assembler.EducationCourseClassSignInAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.signin.EducationCourseClassSignInPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassSignInDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassSignIn;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassSignInListProcessor extends AbstractEducationClassSignInProcessor<EducationCourseClassSignInPageRequest, BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassSignInPageRequest request, BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>> response) {
        request.initPage();

        EducationCourseClassSignIn educationCourseClassSignIn = EducationCourseClassSignInAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseClassSignIn>> listPageResponse = educationCourseClassSignInRepository.listPage(educationCourseClassSignIn, pageRequest);

        PageResponseDTO<EducationCourseClassSignInDTO> list = EducationCourseClassSignInAssembler.INSTANCE.convertPage(listPageResponse);

        response.setData(list);
    }

}
