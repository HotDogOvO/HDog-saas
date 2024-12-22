package com.hotdog.saas.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-14 21:40:04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_role")
public class RoleDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 角色名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

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
