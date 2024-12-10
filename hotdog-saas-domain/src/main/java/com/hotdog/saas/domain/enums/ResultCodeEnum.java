package com.hotdog.saas.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS("0", "请求成功"),
    DATABASE_UPDATE_FAIL("2", "数据库升级异常"),


    TIMESTAMP_INVALID("99996", "时间戳非法"),
    TIMESTAMP_EMPTY("99996", "时间戳为空"),
    HTTP_METHOD_INVALID("99997", "HTTP请求非法"),
    SIGN_FAIL("99998", "签名非法"),
    SIGN_EMPTY("99998", "签名为空"),
    FAIL("99999", "请求失败"),
    ;

    private final String code;

    private final String message;

}
