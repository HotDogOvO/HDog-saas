package com.hotdog.saas.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    // 成功
    SUCCESS("success", "请求成功"),

    // 系统异常
    DATABASE_UPDATE_FAIL("database_update_fail", "数据库升级异常"),
    TIMESTAMP_INVALID("timestamp_invalid", "时间戳非法"),
    TIMESTAMP_EMPTY("timestamp_empty", "时间戳为空"),
    HTTP_METHOD_INVALID("http_method_invalid", "HTTP请求非法"),
    SIGN_FAIL("sign_fail", "签名非法"),
    SIGN_EMPTY("sign_empty", "签名为空"),
    PARAMS_INVALID("params_invalid", "请求参数非法"),
    FAIL("fail", "请求失败"),
    ;

    private final String code;

    private final String message;

}
