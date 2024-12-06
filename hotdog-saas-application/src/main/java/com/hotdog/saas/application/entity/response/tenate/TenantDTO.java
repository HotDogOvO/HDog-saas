package com.hotdog.saas.application.entity.response.tenate;


import com.hotdog.saas.application.entity.response.common.BaseResponse;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenantDTO {

    /**
     * 租户ID
     */
    private Integer id;

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
    private Boolean status;

    /**
     * 是否删除（0 正常 1 删除）
     */
    private Boolean deleted;

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
