package com.hotdog.saas.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationLog {
    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作类型（1：查询，2：新增，3：更新，4：删除）
     * @see com.hotdog.saas.domain.enums.log.LogOperationEnum
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
