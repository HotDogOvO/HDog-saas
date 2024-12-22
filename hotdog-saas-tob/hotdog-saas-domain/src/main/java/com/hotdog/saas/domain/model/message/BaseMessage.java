package com.hotdog.saas.domain.model.message;

import lombok.Data;

@Data
public class BaseMessage {

    /**
     * 补偿任务ID
     */
    private Long messageId;

    /**
     * 是否为补偿任务
     */
    private Boolean isRetry;
}
