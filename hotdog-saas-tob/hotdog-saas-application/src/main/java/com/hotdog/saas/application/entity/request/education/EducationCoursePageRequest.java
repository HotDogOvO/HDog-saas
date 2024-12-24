package com.hotdog.saas.application.entity.request.education;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "分页课程DTO", description = "分页课程DTO")
public class EducationCoursePageRequest extends PageRequestParam {

}
