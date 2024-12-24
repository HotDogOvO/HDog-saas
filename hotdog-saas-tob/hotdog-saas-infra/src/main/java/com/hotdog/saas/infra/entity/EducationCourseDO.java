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
 * 课程信息表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-24 15:07:03
 */
@Getter
@Setter
@TableName("c_education_course")
public class EducationCourseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 微信小程序ID
     */
    private Long wechatAppId;

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
     * 课程类型（1:线下 2：线上 3：录像）
     */
    private Byte courseType;

    /**
     * 课程价格
     */
    private BigDecimal coursePrice;

    /**
     * 状态（0正常 1停用）
     */
    private Boolean status;

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
