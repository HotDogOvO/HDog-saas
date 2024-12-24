package com.hotdog.saas.infra.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程班级人员表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-24 15:07:54
 */
@Getter
@Setter
@TableName("c_education_course_class_person")
public class EducationCourseClassPersonDO implements Serializable {

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
     * 人员类型（1：教师 2：学员）
     * @see com.hotdog.saas.domain.enums.education.CourseClassPersonTypeEnum
     */
    private Integer type;

    /**
     * 人员openId
     */
    private String peopleOpenId;

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
     * 更新时间
     */
    private LocalDateTime updateTime;
}
