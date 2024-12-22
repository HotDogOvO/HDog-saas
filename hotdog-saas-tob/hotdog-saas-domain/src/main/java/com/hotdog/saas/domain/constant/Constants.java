package com.hotdog.saas.domain.constant;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 系统常量
 * @author hotdog
 * @date 2024/12/19 16:18
 */
public class Constants {

    /** 分布式锁默认时间 */
    public static final Long LOCK_TIME_SECONDS = 60L;

    /** 系统时区 */
    public static final String SYSTEM_TIME_ZONE = "Asia/Shanghai";

    /** 系统操作人 */
    public static final String SYSTEM_OPERATOR = "system";

    /** 系统请求method，只开放post */
    public static final String HTTP_POST = "POST";

    /** 新增用户默认密码 */
    public static final String DEFAULT_PASSWORD = "123456";

    /** 默认头像 */
    public static final String DEFAULT_AVATAR = "avatar.jpg";

    /** 默认菜单icon */
    public static final String DEFAULT_MENU_ICON = "default";

    /** 请求头-token */
    public static final String HEADER_TOKEN_KEY = "Authorization";

    /** 请求头-ip */
    public static final String[] HEADER_IP_KEY = new String[]{
            "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"
    };

    /** 请求头-UserAgent */
    public static final String HEADER_USER_AGENT_KEY = "User-Agent";

    /** 不拦截token的接口 */
    public static final String[] TOKEN_EXCLUDE_PATTERN_URL = new String[]{
            "/api/hotdog/v1/login"
    };

    /** 需要记录操作日志的表 */
    public static final List<String> NEED_RECORD_OPERATION_LOG_TABLE = Lists.newArrayList(
            "t_tenant", "t_user", "t_role", "t_menu", "t_system_properties"
    );

    // ======================= SYSTEM PATH =======================
    /** 工作目录 */
    public static final String PROJECT_WORK_PATH = System.getProperty("user.dir");

    /** SQL存放目录 */
    public static final String PROJECT_SQL_PATH = PROJECT_WORK_PATH + "/CICD/sql";

    // ======================= DB COLUMN =======================

    /** 系统版本号（配置表中的name） */
    public static final String DB_COLUMN_VERSION = "version";

    // ======================= 系统符号 =======================
    public static final String CN_COMMA = "，";

    public static final String EN_COMMA = ",";
}
