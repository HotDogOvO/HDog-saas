package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.education.clazz.schedule.CreateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.DeleteEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassSchedulePageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.UpdateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleDTO;
import com.hotdog.saas.application.facade.EducationCourseClassScheduleFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "课程表管理")
@RestController
@RequestMapping("/api/hotdog/v1/education/course/class/schedule")
public class EducationCourseClassScheduleController {

    private final EducationCourseClassScheduleFacade educationCourseClassScheduleFacade;

    public EducationCourseClassScheduleController(EducationCourseClassScheduleFacade educationCourseClassScheduleFacade) {
        this.educationCourseClassScheduleFacade = educationCourseClassScheduleFacade;
    }

    @Operation(summary = "创建课程表")
    @PostMapping("/create")
    public BaseResponse<Boolean> createEducationCourseClassSchedule(@RequestBody @Validated CreateEducationCourseClassScheduleRequest createEducationCourseClassScheduleRequest) {
        return educationCourseClassScheduleFacade.createEducationCourseClassSchedule(createEducationCourseClassScheduleRequest);
    }

    @Operation(summary = "查询课程表分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<EducationCourseClassScheduleDTO>> EducationCourseClassScheduleListPage(@RequestBody @Validated EducationCourseClassSchedulePageRequest educationCourseClassSchedulePageRequest) {
        return educationCourseClassScheduleFacade.educationCourseClassScheduleListPage(educationCourseClassSchedulePageRequest);
    }

    @Operation(summary = "更新课程表")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateEducationCourseClassSchedule(@RequestBody @Validated UpdateEducationCourseClassScheduleRequest updateEducationCourseClassScheduleRequest) {
        return educationCourseClassScheduleFacade.updateEducationCourseClassSchedule(updateEducationCourseClassScheduleRequest);
    }

    @Operation(summary = "删除课程表")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEducationCourseClassSchedule(@RequestBody @Validated DeleteEducationCourseClassScheduleRequest deleteEducationCourseClassScheduleRequest) {
        return educationCourseClassScheduleFacade.deleteEducationCourseClassSchedule(deleteEducationCourseClassScheduleRequest);
    }

}
