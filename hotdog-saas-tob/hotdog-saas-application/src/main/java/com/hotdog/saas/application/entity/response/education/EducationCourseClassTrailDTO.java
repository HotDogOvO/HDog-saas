package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "试课返回DTO", description = "试课返回DTO")
public class EducationCourseClassTrailDTO {

    @Schema(description = "课程表ID")
    private Long id;

    @Schema(description = "课程编号")
    private String courseNo;

    @Schema(description = "班级编号")
    private String classNo;

    @Schema(description = "人员OpenId")
    private String peopleOpenId;

    @Schema(description = "课时ID")
    private Long classScheduleId;

    @Schema(description = "状态（0：已报名 1：已上课 2：未上课）")
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
