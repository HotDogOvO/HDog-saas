package com.hotdog.saas.application.entity.request.education.clazz.person;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "分页班级人员DTO", description = "分页班级人员DTO")
public class EducationCourseClassPersonPageRequest extends PageRequestParam {

    @NotBlank(message = "班级编号不能为空")
    @Schema(description = "班级编号")
    private String classNo;

}
