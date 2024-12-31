package com.hotdog.saas.domain.model;


import com.hotdog.saas.domain.enums.education.EducationBusinessTypeEnum;
import com.hotdog.saas.domain.utils.BusinessNoUtils;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourse {

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 微信ID
     */
    private Long wechatId;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程描述
     */
    private String description;

    /**
     * 课程类型（1:线下 2：线上）
     * @see com.hotdog.saas.domain.enums.education.CourseTypeEnum
     */
    private Integer courseType;

    /**
     * 课程价格
     */
    private BigDecimal coursePrice;

    /**
     * 状态（0正常 1停用）
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

    /**
     * 操作人
     */
    private String operator;

    /**
     * 生成业务编号
     */
    public void generateBusinessNo(){
        this.courseNo = BusinessNoUtils.generateEducationBusinessNo(EducationBusinessTypeEnum.EDUCATION_COURSE);
    }
}
