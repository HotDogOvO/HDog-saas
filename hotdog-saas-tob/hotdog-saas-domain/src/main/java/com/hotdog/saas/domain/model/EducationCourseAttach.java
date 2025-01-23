package com.hotdog.saas.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseAttach {

    private Long id;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 附件类型
     * @see com.hotdog.saas.domain.enums.education.CourseAttachTypeEnum
     */
    private Integer attachType;

    /**
     * 附件名
     */
    private String attachName;

    /**
     * 附件路径
     */
    private String attachUrl;

    /**
     * 是否删除（0 正常 1 删除）
     */
    private Integer deleted;

    /**
     * 操作人
     */
    private String operator;
}
