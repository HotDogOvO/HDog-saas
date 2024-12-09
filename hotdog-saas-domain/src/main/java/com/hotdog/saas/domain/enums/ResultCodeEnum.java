package com.hotdog.saas.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS("success", "请求成功"),
    FAIL("fail", "请求失败"),
    DATABASE_UPDATE_FAIL("database_update_fail", "数据库升级异常"),
    ;

    private final String code;

    private final String message;

}
