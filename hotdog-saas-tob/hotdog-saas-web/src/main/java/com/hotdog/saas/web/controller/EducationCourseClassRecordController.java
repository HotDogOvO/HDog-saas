package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.education.clazz.person.EducationCourseClassPersonPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.record.EducationCourseClassRecordPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassPersonDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassRecordDTO;
import com.hotdog.saas.application.facade.EducationCourseClassPersonFacade;
import com.hotdog.saas.application.facade.EducationCourseClassRecordFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "授课记录管理")
@RestController
@RequestMapping("/api/hotdog/v1/education/course/class/record")
public class EducationCourseClassRecordController {

    private final EducationCourseClassRecordFacade educationCourseClassRecordFacade;

    public EducationCourseClassRecordController(EducationCourseClassRecordFacade educationCourseClassRecordFacade) {
        this.educationCourseClassRecordFacade = educationCourseClassRecordFacade;
    }

    @Operation(summary = "查询授课记录分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<EducationCourseClassRecordDTO>> educationCourseClassRecordListPage(@RequestBody @Validated EducationCourseClassRecordPageRequest educationCourseClassRecordPageRequest) {
        return educationCourseClassRecordFacade.educationCourseClassPersonListPage(educationCourseClassRecordPageRequest);
    }

}
