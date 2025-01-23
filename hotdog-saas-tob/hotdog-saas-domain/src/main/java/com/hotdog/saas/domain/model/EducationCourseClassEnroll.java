package com.hotdog.saas.domain.model;

import com.hotdog.saas.domain.enums.education.CourseClassAssignStatusEnum;
import com.hotdog.saas.domain.enums.pay.PayStatusEnum;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseClassEnroll {

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

    public boolean isPay(){
        PayStatusEnum payStatusEnum = PayStatusEnum.codeToEnum(this.payStatus);
        return PayStatusEnum.paySuccessList().contains(payStatusEnum);
    }

    public boolean isAssign(){
        CourseClassAssignStatusEnum assignStatusEnum = CourseClassAssignStatusEnum.codeToEnum(this.assignStatus);
        return assignStatusEnum.equals(CourseClassAssignStatusEnum.ASSIGN);
    }
}
