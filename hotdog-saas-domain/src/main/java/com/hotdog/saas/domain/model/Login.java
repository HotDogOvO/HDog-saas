package com.hotdog.saas.domain.model;

import com.hotdog.saas.domain.utils.SignUtils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Login {

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 状态（0正常 1停用）
     */
    private Integer status;

}