package com.hotdog.saas.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaDeadMessage {

    /**
     * 主键
     */
    private Long id;

    /**
     * 失败任务topic
     */
    private String kafkaTopic;

    /**
     * 失败任务所在分区
     */
    private String kafkaPartition;

    /**
     * 失败任务key
     */
    private String kafkaKey;

    /**
     * 失败任务数据
     */
    private String kafkaValue;

    /**
     * 重试状态（0：未重试，1：重试成功，2：重试失败）
     *
     * @see com.hotdog.saas.domain.enums.kafka.KafkaDeadMessageStatusEnum
     */
    private Integer status;

    /**
     * 重试时间
     */
    private LocalDateTime retryTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
