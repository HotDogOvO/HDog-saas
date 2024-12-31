package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "课程表日历返回DTO", description = "课程表日历返回DTO")
public class EducationCourseClassScheduleCalendarDTO {

    @Schema(description = "日历")
    private String date;

    @Schema(description = "课程表")
    private List<EducationCourseClassScheduleDTO> dataList;

}
