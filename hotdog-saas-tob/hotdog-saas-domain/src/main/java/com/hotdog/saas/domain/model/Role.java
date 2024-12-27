package com.hotdog.saas.domain.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Role {

    /**
     * 角色ID
     */
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
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 操作人
     */
    private String operator;
}
