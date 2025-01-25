package com.hotdog.saas.matrix.web.controller;

import com.hotdog.saas.application.entity.request.education.clazz.signin.DeleteEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.request.education.clazz.signin.EducationCourseClassSignInPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.signin.UpdateEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassSignInDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassSignInDTO;
import com.hotdog.saas.application.facade.EducationCourseClassSignInFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "课程签到管理")
@RestController
@RequestMapping("/api/hotdog/v1/education/course/class/sign-in")
public class EducationCourseClassSignInController {

    private final EducationCourseClassSignInFacade educationCourseClassSignInFacade;

    public EducationCourseClassSignInController(EducationCourseClassSignInFacade educationCourseClassSignInFacade) {
        this.educationCourseClassSignInFacade = educationCourseClassSignInFacade;
    }

    @Operation(summary = "查询签到分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>> educationCourseClassSignInListPage(@RequestBody @Validated EducationCourseClassSignInPageRequest educationCourseClassSignInPageRequest) {
        return educationCourseClassSignInFacade.educationCourseClassSignInListPage(educationCourseClassSignInPageRequest);
    }

    @Operation(summary = "修改签到记录")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateEducationCourseClassSignIn(@RequestBody @Validated UpdateEducationCourseClassSignInRequest updateEducationCourseClassSignInRequest) {
        return educationCourseClassSignInFacade.updateEducationCourseClassSignIn(updateEducationCourseClassSignInRequest);
    }

    @Operation(summary = "删除签到记录")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEducationCourseClassSignIn(@RequestBody @Validated DeleteEducationCourseClassSignInRequest deleteEducationCourseClassSignInRequest) {
        return educationCourseClassSignInFacade.deleteEducationCourseClassSignIn(deleteEducationCourseClassSignInRequest);
    }

}
