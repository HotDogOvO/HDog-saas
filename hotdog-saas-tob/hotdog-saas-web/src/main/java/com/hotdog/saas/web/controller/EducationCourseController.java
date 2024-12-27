package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.DeleteEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.EducationCourseOptionsRequest;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.request.education.QueryEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.UpdateEducationCourseRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseOptionsDTO;
import com.hotdog.saas.application.facade.EducationCourseFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "课程管理")
@RestController
@RequestMapping("/api/hotdog/v1/education/course")
public class EducationCourseController {

    private final EducationCourseFacade educationCourseFacade;

    public EducationCourseController(EducationCourseFacade educationCourseFacade) {
        this.educationCourseFacade = educationCourseFacade;
    }

    @Operation(summary = "创建课程")
    @PostMapping("/create")
    public BaseResponse<Boolean> createEducationCourse(@RequestBody @Validated CreateEducationCourseRequest createEducationCourseRequest) {
        return educationCourseFacade.createEducationCourse(createEducationCourseRequest);
    }

    @Operation(summary = "查询课程分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<EducationCourseDTO>> educationCourseListPage(@RequestBody @Validated EducationCoursePageRequest EducationCoursePageRequest) {
        return educationCourseFacade.educationCourseListPage(EducationCoursePageRequest);
    }

    @Operation(summary = "查询课程下拉框")
    @PostMapping("/options")
    public BaseResponse<List<EducationCourseOptionsDTO>> educationCourseOptions(@RequestBody @Validated EducationCourseOptionsRequest educationCourseOptionsRequest) {
        return educationCourseFacade.educationCourseOptions(educationCourseOptionsRequest);
    }

    @Operation(summary = "查询课程详情")
    @PostMapping("/detail")
    public BaseResponse<EducationCourseDTO> educationCourseDetail(@RequestBody @Validated QueryEducationCourseRequest queryEducationCourseRequest) {
        return educationCourseFacade.educationCourseDetail(queryEducationCourseRequest);
    }

    @Operation(summary = "更新课程")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateEducationCourse(@RequestBody @Validated UpdateEducationCourseRequest updateEducationCourseRequest) {
        return educationCourseFacade.updateEducationCourse(updateEducationCourseRequest);
    }

    @Operation(summary = "删除课程")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEducationCourse(@RequestBody @Validated DeleteEducationCourseRequest deleteEducationCourseRequest) {
        return educationCourseFacade.deleteEducationCourse(deleteEducationCourseRequest);
    }

}
