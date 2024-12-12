package com.hotdog.saas.infra.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统配置表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-09 09:58:22
 */
@Getter
@Setter
@TableName("system_properties")
public class SystemPropertiesDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 配置key
     */
    private String name;

    /**
     * 配置value
     */
    private String value;

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
     * 创建时间
     */
    private LocalDateTime updateTime;
}
