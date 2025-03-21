package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.education.clazz.enroll.AssignEducationCourseClassEnrollRequest;
import com.hotdog.saas.application.entity.request.education.clazz.enroll.DeleteEducationCourseClassEnrollRequest;
import com.hotdog.saas.application.entity.request.education.clazz.enroll.EducationCourseClassEnrollPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassEnrollDTO;
import com.hotdog.saas.application.facade.EducationCourseClassEnrollFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "课程报名管理")
@RestController
@RequestMapping("/api/hotdog/v1/education/course/class/enroll")
public class EducationCourseClassEnrollController {

    private final EducationCourseClassEnrollFacade educationCourseClassEnrollFacade;

    public EducationCourseClassEnrollController(EducationCourseClassEnrollFacade educationCourseClassEnrollFacade) {
        this.educationCourseClassEnrollFacade = educationCourseClassEnrollFacade;
    }

    @Operation(summary = "查询报名分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>> educationCourseClassEnrollListPage(@RequestBody @Validated EducationCourseClassEnrollPageRequest educationCourseClassEnrollPageRequest) {
        return educationCourseClassEnrollFacade.educationCourseClassEnrollListPage(educationCourseClassEnrollPageRequest);
    }

    @Operation(summary = "分配报名班级")
    @PostMapping("/assign")
    public BaseResponse<Boolean> assignEducationCourseClassEnroll(@RequestBody @Validated AssignEducationCourseClassEnrollRequest assignEducationCourseClassEnrollRequest) {
        return educationCourseClassEnrollFacade.assignEducationCourseClassEnroll(assignEducationCourseClassEnrollRequest);
    }

    @Operation(summary = "删除报名记录")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEducationCourseClassEnroll(@RequestBody @Validated DeleteEducationCourseClassEnrollRequest deleteEducationCourseClassEnrollRequest) {
        return educationCourseClassEnrollFacade.deleteEducationCourseClassEnroll(deleteEducationCourseClassEnrollRequest);
    }

}
