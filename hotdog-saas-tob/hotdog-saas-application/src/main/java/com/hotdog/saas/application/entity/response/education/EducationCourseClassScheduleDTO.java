package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "课程表返回DTO", description = "课程表返回DTO")
public class EducationCourseClassScheduleDTO {

    @Schema(description = "课程表ID")
    private Long id;

    @Schema(description = "班级编号")
    private String classNo;

    @Schema(description = "课时名")
    private String classHoursName;

    @Schema(description = "课程开始时间")
    private LocalDateTime classBeginTime;

    @Schema(description = "课程结束时间")
    private LocalDateTime classEndTime;

    @Schema(description = "状态（1：待开班 2：开班中 3：结业 9：取消）")
    private Integer status;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
