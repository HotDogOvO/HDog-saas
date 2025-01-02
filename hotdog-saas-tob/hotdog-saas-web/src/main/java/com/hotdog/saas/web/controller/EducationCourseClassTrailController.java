package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.education.clazz.trail.AssignEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.DeleteEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.EducationCourseClassTrailPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassTrailDTO;
import com.hotdog.saas.application.facade.EducationCourseClassTrailFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "课程试课管理")
@RestController
@RequestMapping("/api/hotdog/v1/education/course/class/trail")
public class EducationCourseClassTrailController {

    private final EducationCourseClassTrailFacade educationCourseClassTrailFacade;

    public EducationCourseClassTrailController(EducationCourseClassTrailFacade educationCourseClassTrailFacade) {
        this.educationCourseClassTrailFacade = educationCourseClassTrailFacade;
    }

    @Operation(summary = "查询试课分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<EducationCourseClassTrailDTO>> educationCourseClassTrailListPage(@RequestBody @Validated EducationCourseClassTrailPageRequest educationCourseClassTrailPageRequest) {
        return educationCourseClassTrailFacade.educationCourseClassTrailListPage(educationCourseClassTrailPageRequest);
    }

    @Operation(summary = "分配试课班级")
    @PostMapping("/assign")
    public BaseResponse<Boolean> assignEducationCourseClassTrail(@RequestBody @Validated AssignEducationCourseClassTrailRequest assignEducationCourseClassTrailRequest) {
        return educationCourseClassTrailFacade.assignEducationCourseClassTrail(assignEducationCourseClassTrailRequest);
    }

    @Operation(summary = "删除试课")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEducationCourseClassTrail(@RequestBody @Validated DeleteEducationCourseClassTrailRequest deleteEducationCourseClassTrailRequest) {
        return educationCourseClassTrailFacade.deleteEducationCourseClassTrail(deleteEducationCourseClassTrailRequest);
    }

}
