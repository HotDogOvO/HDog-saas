package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.education.clazz.person.CreateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.DeleteEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.EducationCourseClassPersonPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.UpdateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassPersonDTO;
import com.hotdog.saas.application.facade.EducationCourseClassPersonFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "班级人员管理")
@RestController
@RequestMapping("/api/hotdog/v1/education/course/class/person")
public class EducationCourseClassPersonController {

    private final EducationCourseClassPersonFacade educationCourseClassPersonFacade;

    public EducationCourseClassPersonController(EducationCourseClassPersonFacade educationCourseClassPersonFacade) {
        this.educationCourseClassPersonFacade = educationCourseClassPersonFacade;
    }

    @Operation(summary = "创建班级人员")
    @PostMapping("/create")
    public BaseResponse<Boolean> createEducationCourseClassPerson(@RequestBody @Validated CreateEducationCourseClassPersonRequest createEducationCourseClassPersonRequest) {
        return educationCourseClassPersonFacade.createEducationCourseClassPerson(createEducationCourseClassPersonRequest);
    }

    @Operation(summary = "查询班级人员分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<EducationCourseClassPersonDTO>> EducationCourseClassPersonListPage(@RequestBody @Validated EducationCourseClassPersonPageRequest educationCourseClassPersonPageRequest) {
        return educationCourseClassPersonFacade.educationCourseClassPersonListPage(educationCourseClassPersonPageRequest);
    }

    @Operation(summary = "更新班级人员")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateEducationCourseClassPerson(@RequestBody @Validated UpdateEducationCourseClassPersonRequest updateEducationCourseClassPersonRequest) {
        return educationCourseClassPersonFacade.updateEducationCourseClassPerson(updateEducationCourseClassPersonRequest);
    }

    @Operation(summary = "删除班级人员")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEducationCourseClassPerson(@RequestBody @Validated DeleteEducationCourseClassPersonRequest deleteEducationCourseClassPersonRequest) {
        return educationCourseClassPersonFacade.deleteEducationCourseClassPerson(deleteEducationCourseClassPersonRequest);
    }

}
