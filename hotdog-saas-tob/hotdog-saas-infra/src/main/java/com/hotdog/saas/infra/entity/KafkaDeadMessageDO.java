package com.hotdog.saas.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Kafka死信队列表
 * </p>
 *
 * @author hotdog
 * @since 2024-12-19 17:22:44
 */
@Getter
@Setter
@TableName("s_kafka_dead_message")
public class KafkaDeadMessageDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
