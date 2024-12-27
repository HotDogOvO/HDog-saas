package com.hotdog.saas.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Menu{

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 菜单类型（1：菜单 2：按钮）
     */
    private Integer type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单icon
     */
    private String icon;

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
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 子菜单
     */
    private List<Menu> children;
}
