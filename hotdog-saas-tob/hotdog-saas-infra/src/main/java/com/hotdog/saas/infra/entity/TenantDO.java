package com.hotdog.saas.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-06 14:12:41
 */
@Data
@Accessors(chain = true)
@TableName("t_tenant")
public class TenantDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户名
     */
    private String name;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人手机号
     */
    private String contractPhone;

    /**
     * 联系人邮箱
     */
    private String contractEmail;

    /**
     * appId
     */
    private String appId;

    /**
     * app秘钥
     */
    private String appSecret;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

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
