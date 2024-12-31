package com.hotdog.saas.application.entity.response.education;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "班级下拉框返回DTO", description = "班级下拉框返回DTO")
public class EducationCourseClassOptionsDTO {

    @Schema(description = "班级编号")
    private String classNo;

    @Schema(description = "班级名称")
    private String name;

}
