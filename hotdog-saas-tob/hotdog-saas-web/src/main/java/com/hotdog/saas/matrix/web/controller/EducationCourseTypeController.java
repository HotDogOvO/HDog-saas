package com.hotdog.saas.matrix.web.controller;

import com.hotdog.saas.application.entity.request.education.type.CreateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.request.education.type.DeleteEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypeOptionsRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypePageRequest;
import com.hotdog.saas.application.entity.request.education.type.UpdateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeOptionsDTO;
import com.hotdog.saas.application.facade.EducationCourseTypeFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "课程分类管理")
@RestController
@RequestMapping("/api/hotdog/v1/education/course/type")
public class EducationCourseTypeController {

    private final EducationCourseTypeFacade educationCourseTypeFacade;

    public EducationCourseTypeController(EducationCourseTypeFacade educationCourseTypeFacade) {
        this.educationCourseTypeFacade = educationCourseTypeFacade;
    }

    @Operation(summary = "创建课程分类")
    @PostMapping("/create")
    public BaseResponse<Boolean> createEducationCourseType(@RequestBody @Validated CreateEducationCourseTypeRequest createEducationCourseTypeRequest) {
        return educationCourseTypeFacade.createEducationCourseType(createEducationCourseTypeRequest);
    }

    @Operation(summary = "查询课程分类分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<EducationCourseTypeDTO>> educationCourseTypeListPage(@RequestBody @Validated EducationCourseTypePageRequest educationCourseTypePageRequest) {
        return educationCourseTypeFacade.educationCourseTypeListPage(educationCourseTypePageRequest);
    }

    @Operation(summary = "查询课程下拉框")
    @PostMapping("/options")
    public BaseResponse<List<EducationCourseTypeOptionsDTO>> educationCourseTypeOptions(@RequestBody @Validated EducationCourseTypeOptionsRequest educationCourseTypeOptionsRequest) {
        return educationCourseTypeFacade.educationCourseTypeOptions(educationCourseTypeOptionsRequest);
    }

    @Operation(summary = "更新课程分类")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateEducationCourseType(@RequestBody @Validated UpdateEducationCourseTypeRequest updateEducationCourseTypeRequest) {
        return educationCourseTypeFacade.updateEducationCourseType(updateEducationCourseTypeRequest);
    }

    @Operation(summary = "删除课程分类")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEducationCourseType(@RequestBody @Validated DeleteEducationCourseTypeRequest deleteEducationCourseTypeRequest) {
        return educationCourseTypeFacade.deleteEducationCourseType(deleteEducationCourseTypeRequest);
    }

}
