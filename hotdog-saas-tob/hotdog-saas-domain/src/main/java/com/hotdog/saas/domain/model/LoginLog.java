package com.hotdog.saas.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginLog {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 日志类型
     */
    private Integer logType;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录时间
     */
    private LocalDateTime loginDate;

    /**
     * UserAgent
     */
    private String userAgent;

    /**
     * 是否成功（0：成功，1：失败）
     */
    private Integer success;

    /**
     * 失败原因
     */
    private String errorMessage;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 操作人
     */
    private String operator;

}
