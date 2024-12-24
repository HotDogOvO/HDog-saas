package com.hotdog.saas.infra.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 班级课程表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-24 15:07:54
 */
@Getter
@Setter
@TableName("c_education_course_class_schedule")
public class EducationCourseClassScheduleDO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 是否删除（0 正常 1 删除）
     */
    private Integer deleted;

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
     * 创建时间
     */
    private LocalDateTime updateTime;
}
