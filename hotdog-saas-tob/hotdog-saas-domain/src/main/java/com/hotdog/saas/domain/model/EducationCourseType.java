package com.hotdog.saas.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseType {

    private Long id;

    /**
     * 微信ID
     */
    private Long wechatId;

    /**
     * 课程分类名
     */
    private String typeName;

    /**
     * 分类备注
     */
    private String remark;

    /**
     * 状态（0正常 1停用）
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
     * 创建时间
     */
    private LocalDateTime updateTime;

    /**
     * 操作人
     */
    private String operator;
}
