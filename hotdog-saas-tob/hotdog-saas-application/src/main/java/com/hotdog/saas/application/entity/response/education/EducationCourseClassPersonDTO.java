package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "班级人员返回DTO", description = "班级人员返回DTO")
public class EducationCourseClassPersonDTO {

    @Schema(description = "课程表ID")
    private Long id;

    @Schema(description = "班级编号")
    private String classNo;

    @Schema(description = "人员类型（1：教师 2：学员）")
    private Integer type;

    @Schema(description = "人员OpenId")
    private String peopleOpenId;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
