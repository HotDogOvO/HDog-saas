package com.hotdog.saas.application.entity.request.education.type;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.enums.education.CourseTypeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "课程类型列表DTO", description = "课程类型列表DTO")
public class EducationCourseTypeListRequest extends BaseRequestParam {

    @NotNull(message = "微信ID不能为空")
    @Schema(description = "微信ID")
    private Long wechatId;

    @Override
    public void validate() {
    }
}
