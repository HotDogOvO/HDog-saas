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
 * 微信小程序表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-20 13:50:26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_wechat_app")
public class WechatAppDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 小程序主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
}
