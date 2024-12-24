package com.hotdog.saas.infra.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程授课记录表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-24 15:07:54
 */
@Getter
@Setter
@TableName("c_education_course_class_record")
public class EducationCourseClassRecordDO implements Serializable {

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
     * 是否删除（0 正常 1 删除）
     */
    private Boolean deleted;

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
