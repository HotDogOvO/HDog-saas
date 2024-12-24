package com.hotdog.saas.application.entity.request.education;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.enums.education.CourseTypeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "创建课程DTO", description = "创建课程DTO")
public class CreateEducationCourseRequest extends BaseRequestParam {

    @NotNull(message = "微信ID不能为空")
    @Schema(description = "微信ID")
    private Long wechatId;

    @NotBlank(message = "课程名称不能为空")
    @Schema(description = "课程名称")
    private String name;

    @Schema(description = "课程描述")
    private String description;

    @NotNull(message = "课程类型不能为空")
    @Schema(description = "课程类型（1:线下 2：线上）")
    private Integer courseType;

    @NotNull(message = "课程价格不能为空")
    @Schema(description = "课程价格")
    private BigDecimal coursePrice;

    @Override
    public void validate() {
        CourseTypeEnum courseTypeEnum = CourseTypeEnum.codeToEnum(courseType);
        if(courseTypeEnum == CourseTypeEnum.UNKNOWN) {
            throw new BusinessException("课程类型未知");
        }
    }
}
