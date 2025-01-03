package com.hotdog.saas.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 报名管理表
 * </p>
 *
 * @author hotdog
 * @since 2025-01-03 14:18:43
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("c_education_course_class_enroll")
public class EducationCourseClassEnrollDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 微信ID
     */
    private Long wechatId;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 报名人openId
     */
    private String peopleOpenId;

    /**
     * 支付状态（0：未支付 1：已支付）
     * @see com.hotdog.saas.domain.enums.pay.PayStatusEnum
     */
    private Integer payStatus;

    /**
     * 班级分配状态（0：未分配 1：已分配）
     * @see com.hotdog.saas.domain.enums.education.CourseClassAssignStatusEnum
     */
    private Integer assignStatus;

    /**
     * 状态（1：报名成功 2：班级分配中 3：班级分配完成）
     * @see com.hotdog.saas.domain.enums.education.CourseClassEnrollStatusEnum
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
     * 更新时间
     */
    private LocalDateTime updateTime;
}
