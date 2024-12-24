package com.hotdog.saas.domain.utils;

/**
 * 雪花ID生成器
 * @author hotdog
 * @date 2024/12/24 18:22
 */
public class SnowFlakeIdGenerator {

    private static final Long sequenceBits = 12L;
    private static final Long sequenceMask = ~(-1L << sequenceBits);
    private static final Long workerIdBits = 5L;
    private static final Long workerIdShift = sequenceBits;
    private static final Long dataCenterIdBits = 5L;
    private static final Long dataCenterIdShift = sequenceBits + workerIdBits;
    private static final Long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private static final Long twepoch = 1656739812000L;
    private static final Long workerId = 1L;
    private static final Long dataCenterId = 1L;
    private static final Long customId = 1L;
    private static Long lastTimestamp = -1L;
    private static Long sequence = 0L;

    /**
     * 获取雪花ID
     */
    public static String nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        long low = ((timestamp - twepoch) << timestampLeftShift) // 时间戳部分
                | (dataCenterId << dataCenterIdShift) // 数据中心部分
                | (workerId << workerIdShift) // 机器标识部分
                | sequence; // 序列号部分;

        return customId + String.format("%019d", low);
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private static Long timeGen() {
        return System.currentTimeMillis();
    }
}
