package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "授课记录返回DTO", description = "授课记录返回DTO")
public class EducationCourseClassRecordDTO {

    @Schema(description = "班级编号")
    private String classNo;

    @Schema(description = "课程开始时间")
    private LocalDateTime classBeginTime;

    @Schema(description = "课程结束时间")
    private LocalDateTime classEndTime;

    @Schema(description = "授课教师")
    private String teacher;

    @Schema(description = "上课人数")
    private Integer studentCount;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
