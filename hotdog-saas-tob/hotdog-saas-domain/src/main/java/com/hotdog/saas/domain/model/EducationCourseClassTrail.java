package com.hotdog.saas.domain.model;

import com.hotdog.saas.domain.enums.education.CourseClassAssignStatusEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseClassTrail implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 状态（0：已报名 1：已上课 2：未上课）
     * @see com.hotdog.saas.domain.enums.education.CourseClassTrailStatusEnum
     */
    private Integer status;

    /**
     * 班级分配状态（0：未分配 1：已分配）
     * @see com.hotdog.saas.domain.enums.education.CourseClassAssignStatusEnum
     */
    private Integer assignStatus;

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

    /**
     * 操作人
     */
    private String operator;

    public boolean isAssign(){
        CourseClassAssignStatusEnum assignStatusEnum = CourseClassAssignStatusEnum.codeToEnum(this.assignStatus);
        return assignStatusEnum.equals(CourseClassAssignStatusEnum.ASSIGN);
    }

}
