package com.hotdog.saas.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseClassPerson {

    /**
     * 班级人员主键
     */
    private Long id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 人员类型（1：教师 2：学员）
     * @see com.hotdog.saas.domain.enums.education.CourseClassPersonTypeEnum
     */
    private Integer type;

    /**
     * 人员openId
     */
    private String peopleOpenId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 操作人
     */
    private String operator;
}
