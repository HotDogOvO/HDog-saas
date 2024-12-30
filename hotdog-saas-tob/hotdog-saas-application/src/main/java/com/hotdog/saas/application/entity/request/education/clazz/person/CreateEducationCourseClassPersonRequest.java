package com.hotdog.saas.application.entity.request.education.clazz.person;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.exception.BusinessException;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "创建班级人员DTO", description = "创建班级人员DTO")
public class CreateEducationCourseClassPersonRequest extends BaseRequestParam {

    @NotBlank(message = "班级编号不能为空")
    @Schema(description = "班级编号")
    private String classNo;

    @NotNull(message = "人员类型不能为空")
    @Schema(description = "人员类型（1：教师 2：学员）")
    private Integer type;

    @NotNull(message = "人员OpenId不能为空")
    @Schema(description = "人员OpenId")
    private String peopleOpenId;

    @Override
    public void validate() {
    }
}
