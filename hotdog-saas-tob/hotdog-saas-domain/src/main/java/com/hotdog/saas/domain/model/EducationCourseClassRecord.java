package com.hotdog.saas.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseClassRecord {

    /**
     * 授课记录ID
     */
    private Long id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 课程开始时间
     */
    private LocalDateTime classBeginTime;

    /**
     * 课程结束时间
     */
    private LocalDateTime classEndTime;

    /**
     * 授课教师
     */
    private String teacher;

    /**
     * 上课人数
     */
    private Integer studentCount;

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
