package com.hotdog.saas.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseTypeRelation {

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 类型ID
     */
    private Long typeId;

}
