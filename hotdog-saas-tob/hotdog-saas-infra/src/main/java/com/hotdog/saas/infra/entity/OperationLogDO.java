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
 * 操作日志表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-18 22:38:37
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("b_operation_log")
public class OperationLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 操作日志ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作类型（1：查询，2：新增，3：更新，4：删除）
     */
    private Integer operationType;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
