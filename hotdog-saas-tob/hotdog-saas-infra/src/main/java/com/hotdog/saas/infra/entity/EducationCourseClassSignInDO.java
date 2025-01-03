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
 * 课程签到表
 * </p>
 *
 * @author hotdog
 * @since 2025-01-03 14:53:11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("c_education_course_class_sign_in")
public class EducationCourseClassSignInDO implements Serializable {

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
     * 班级编号
     */
    private String classNo;

    /**
     * 课时ID
     */
    private Long classScheduleId;

    /**
     * 报名人openId
     */
    private String peopleOpenId;

    /**
     * 签到时间
     */
    private LocalDateTime signInTime;

    /**
     * 状态（1：签到成功 2：签到失败）
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
