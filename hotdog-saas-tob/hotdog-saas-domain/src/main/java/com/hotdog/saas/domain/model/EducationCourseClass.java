package com.hotdog.saas.domain.model;

import com.hotdog.saas.domain.enums.education.EducationBusinessTypeEnum;
import com.hotdog.saas.domain.enums.wechat.app.BusinessTypeEnum;
import com.hotdog.saas.domain.utils.BusinessNoUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseClass {

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

    /**
     * 生成业务编号
     */
    public void generateBusinessNo(){
        this.classNo = BusinessNoUtils.generateEducationBusinessNo(EducationBusinessTypeEnum.EDUCATION_COURSE_CLASS);
    }
}
