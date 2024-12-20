package com.hotdog.saas.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WechatApp {

    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 小程序名称
     */
    private String name;

    /**
     * 微信原始ID
     */
    private String wechatSign;

    /**
     * 微信AppId
     */
    private String wechatAppId;

    /**
     * 微信Secret
     */
    private String wechatAppSecret;

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

    /**
     * 操作人
     */
    private String operator;

}
