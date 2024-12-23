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
 * 登陆日志表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-17 13:16:10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("b_login_log")
public class LoginLogDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 日志类型
     */
    private Byte logType;

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
    private Byte success;

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

}
