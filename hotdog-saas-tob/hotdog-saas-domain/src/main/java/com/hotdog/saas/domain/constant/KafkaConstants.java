package com.hotdog.saas.domain.constant;

/**
 * Kafka常量
 * @author hotdog
 * @date 2024/12/19 16:18
 */
public class KafkaConstants {

    /**
     * canal系统操作日志
     */
    public static final String CANAL_OPERATION_TOPIC = "canal";

    /**
     * 系统全局死信队列
     */
    public static final String DEAD_LETTER_QUEUE_TOPIC = "hdog-saas-dlt";

    /** 补偿任务ID */
    public static final String RETRY_COMPENSATE_ID = "messageId";

    /** 定时补偿任务标识符 */
    public static final String RETRY_COMPENSATE_KEY = "isRetry";

}
