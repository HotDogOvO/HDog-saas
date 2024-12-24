package com.hotdog.saas.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程班级表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-24 15:07:54
 */
@Getter
@Setter
@TableName("c_education_course_class")
public class EducationCourseClassDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 课时
     */
    private String classHour;

    /**
     * 上课时间
     */
    private String classTime;

    /**
     * 报名截止日期
     */
    private LocalDateTime registrationDeadline;

    /**
     * 价格
     */
    private BigDecimal classPrice;

    /**
     * 状态（1：待开班 2：开班中 3：结业 9：取消）
     * @see com.hotdog.saas.domain.enums.education.CourseClassStatusEnum
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
