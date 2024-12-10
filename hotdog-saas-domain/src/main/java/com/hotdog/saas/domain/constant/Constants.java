package com.hotdog.saas.domain.constant;

public class Constants {

    // 分布式锁默认时间
    public static final Long LOCK_TIME_SECONDS = 60L;

    public static final String SYSTEM_OPERATOR = "system";

    public static final String HTTP_POST = "POST";

    // ======================= SYSTEM PATH =======================
    // 工作目录
    public static final String PROJECT_WORK_PATH = System.getProperty("user.dir");

    // SQL存放目录
    public static final String PROJECT_SQL_PATH = PROJECT_WORK_PATH + "/CICD/sql";

    // ======================= DB COLUMN =======================

    // 系统版本号（配置表中的name）
    public static final String DB_COLUMN_VERSION = "version";
}
