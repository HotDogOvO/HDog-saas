package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "课程表下拉框DTO", description = "课程表下拉框DTO")
public class EducationCourseClassScheduleOptionsDTO {

    @Schema(description = "课时ID")
    private Long id;

    @Schema(description = "课时名")
    private String classHoursName;

    @Schema(description = "课程开始时间")
    private LocalDateTime classBeginTime;

    @Schema(description = "课程结束时间")
    private LocalDateTime classEndTime;

    @Schema(description = "状态（1：待开班 2：开班中 3：结业 9：取消）")
    private Integer status;

}
