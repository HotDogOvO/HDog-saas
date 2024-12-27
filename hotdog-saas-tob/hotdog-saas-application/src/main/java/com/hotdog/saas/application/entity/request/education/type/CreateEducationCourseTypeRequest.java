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
@Schema(name = "创建课程类型DTO", description = "创建课程类型DTO")
public class CreateEducationCourseTypeRequest extends BaseRequestParam {

    @NotNull(message = "微信ID不能为空")
    @Schema(description = "微信ID")
    private Long wechatId;

    @NotBlank(message = "课程类型名称不能为空")
    @Schema(description = "课程类型名称")
    private String name;

    @Schema(description = "课程类型备注")
    private String remark;

    @Override
    public void validate() {
    }
}
