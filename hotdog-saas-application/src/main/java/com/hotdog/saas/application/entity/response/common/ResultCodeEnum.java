package com.hotdog.saas.application.entity.response.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS("success", "请求成功"),
    FAIL("fail", "请求失败"),
    ;

    private final String code;

    private final String message;

}
