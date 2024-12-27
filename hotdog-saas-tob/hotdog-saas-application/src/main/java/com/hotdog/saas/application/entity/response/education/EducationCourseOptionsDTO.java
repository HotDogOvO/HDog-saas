package com.hotdog.saas.application.entity.response.education;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "课程下拉框返回DTO", description = "课程下拉框返回DTO")
public class EducationCourseOptionsDTO {

    @Schema(description = "课程编号")
    private String courseNo;

    @Schema(description = "课程名")
    private String name;

}
