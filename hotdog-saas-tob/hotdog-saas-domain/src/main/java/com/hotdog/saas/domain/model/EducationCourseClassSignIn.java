package com.hotdog.saas.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseClassSignIn {

    /**
     * 主键
     */
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
