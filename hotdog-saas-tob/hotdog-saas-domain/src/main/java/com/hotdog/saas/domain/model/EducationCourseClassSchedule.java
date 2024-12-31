package com.hotdog.saas.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseClassSchedule {

    /**
     * 主键
     */
    private Long id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 课时名
     */
    private String classHoursName;

    /**
     * 课程开始时间
     */
    private LocalDateTime classBeginTime;

    /**
     * 课程结束时间
     */
    private LocalDateTime classEndTime;

    /**
     * 状态（1：待开课 2：开课中 3：已下课）
     * @see com.hotdog.saas.domain.enums.education.CourseClassScheduleStatusEnum
     */
    private Integer status;

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
