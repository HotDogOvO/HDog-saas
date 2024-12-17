package com.hotdog.saas.domain.constant;

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

    /** token请求头 */
    public static final String HEADER_TOKEN_KEY = "Authorization";

    /** ip请求头 */
    public static final String[] HEADER_IP_KEY = new String[]{
            "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"
    };

    public static final String HEADER_USER_AGENT_KEY = "User-Agent";

    public static final String[] TOKEN_EXCLUDE_PATTERN_URL = new String[]{
            "/api/hotdog/v1/login"
    };

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
