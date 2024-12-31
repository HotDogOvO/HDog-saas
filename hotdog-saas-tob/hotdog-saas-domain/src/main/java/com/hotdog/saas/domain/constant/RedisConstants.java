package com.hotdog.saas.domain.constant;

/**
 * Redis常量
 *
 * @author hotdog
 * @date 2024/12/19 16:18
 */
public class RedisConstants {

    // ======================= 存储 =======================

    private static final String DEFAULT_KEY_PREFIX = "hotdog:saas:";

    private static final String USER_PREFIX = DEFAULT_KEY_PREFIX + "user:";

    private static final String EDUCATION_PREFIX = "education:";

    // ======================= 延时队列任务 =======================
    private static final String TASK_KEY_PREFIX = DEFAULT_KEY_PREFIX + "task:";

    /** 教育类 - 课程开课定时任务 */
    public static final String EDUCATION_COURSE_START_TASK_PREFIX = DEFAULT_KEY_PREFIX + EDUCATION_PREFIX + "course:start";

    /** 教育类 - 课程下课定时任务 */
    public static final String EDUCATION_COURSE_END_TASK_PREFIX = DEFAULT_KEY_PREFIX + EDUCATION_PREFIX + "course:end";

    // ======================= 锁 =======================
    private static final String LOCK_KEY = "lock:";

    public static final String EDUCATION_COURSE_TASK_LOCK_KEY = DEFAULT_KEY_PREFIX + LOCK_KEY + "course:task";

    // ======================= 时间 =======================
    public static final Long USER_TOKEN_TTL = 86400L;

    public static String getUserKey(String token) {
        return USER_PREFIX + token;
    }
}
