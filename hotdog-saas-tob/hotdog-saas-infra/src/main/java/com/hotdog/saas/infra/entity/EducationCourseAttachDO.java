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
 * 课程附件表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-24 15:07:54
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("c_education_course_attach")
public class EducationCourseAttachDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 附件类型（1：图片 2：视频）
     * @see com.hotdog.saas.domain.enums.education.CourseAttachTypeEnum
     */
    private Integer attachType;

    /**
     * 附件路径
     */
    private String attachUrl;

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
