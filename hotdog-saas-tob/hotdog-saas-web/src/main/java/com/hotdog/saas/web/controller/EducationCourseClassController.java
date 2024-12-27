package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.education.clazz.CreateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.DeleteEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.QueryEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.EducationCourseClassPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.StartEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.UpdateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.application.facade.EducationCourseClassFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "班级管理")
@RestController
@RequestMapping("/api/hotdog/v1/education/course/class")
public class EducationCourseClassController {

    private final EducationCourseClassFacade educationCourseClassFacade;

    public EducationCourseClassController(EducationCourseClassFacade educationCourseClassFacade) {
        this.educationCourseClassFacade = educationCourseClassFacade;
    }

    @Operation(summary = "创建班级")
    @PostMapping("/create")
    public BaseResponse<Boolean> createEducationCourseClass(@RequestBody @Validated CreateEducationCourseClassRequest createEducationCourseClassRequest) {
        return educationCourseClassFacade.createEducationCourseClass(createEducationCourseClassRequest);
    }

    @Operation(summary = "查询班级分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<EducationCourseClassDTO>> educationCourseClassListPage(@RequestBody @Validated EducationCourseClassPageRequest educationCourseClassPageRequest) {
        return educationCourseClassFacade.educationCourseClassListPage(educationCourseClassPageRequest);
    }

    @Operation(summary = "查询课程详情")
    @PostMapping("/detail")
    public BaseResponse<EducationCourseClassDTO> educationCourseClassDetail(@RequestBody @Validated QueryEducationCourseClassRequest queryEducationCourseClassRequest) {
        return educationCourseClassFacade.educationCourseClassDetail(queryEducationCourseClassRequest);
    }

    @Operation(summary = "更新班级")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateEducationCourseClass(@RequestBody @Validated UpdateEducationCourseClassRequest updateEducationCourseClassRequest) {
        return educationCourseClassFacade.updateEducationCourseClass(updateEducationCourseClassRequest);
    }

    @Operation(summary = "开班")
    @PostMapping("/start")
    public BaseResponse<Boolean> startEducationCourseClass(@RequestBody @Validated StartEducationCourseClassRequest startEducationCourseClassRequest) {
        return educationCourseClassFacade.startEducationCourseClass(startEducationCourseClassRequest);
    }

    @Operation(summary = "删除班级")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEducationCourseClass(@RequestBody @Validated DeleteEducationCourseClassRequest deleteEducationCourseClassRequest) {
        return educationCourseClassFacade.deleteEducationCourseClass(deleteEducationCourseClassRequest);
    }

}
