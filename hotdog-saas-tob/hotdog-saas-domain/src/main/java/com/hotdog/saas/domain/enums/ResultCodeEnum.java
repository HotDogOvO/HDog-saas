package com.hotdog.saas.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    // 成功
    SUCCESS("success", "请求成功"),

    // token校验
    TOKEN_EXPIRE("token_expire", "令牌已过期"),
    TOKEN_INVALID("token_invalid", "令牌无效"),
    TOKEN_FAIL("token_fail", "令牌非法"),

    // 系统异常
    FILE_MIDDLE_FAIL("file_middle_fail", "文件中间件异常"),
    DATABASE_UPDATE_FAIL("database_update_fail", "数据库升级异常"),
    TIMESTAMP_INVALID("timestamp_invalid", "时间戳非法"),
    TIMESTAMP_EMPTY("timestamp_empty", "时间戳为空"),
    HTTP_METHOD_INVALID("http_method_invalid", "HTTP请求非法"),
    SIGN_FAIL("sign_fail", "签名非法"),
    SIGN_EMPTY("sign_empty", "签名为空"),
    PARAMS_INVALID("params_invalid", "请求参数非法"),
    FEIGN_CALL_FAIL("feign_call_fail", "远程请求失败"),
    FAIL("fail", "请求失败"),
    ;

    private final String code;

    private final String message;

}
