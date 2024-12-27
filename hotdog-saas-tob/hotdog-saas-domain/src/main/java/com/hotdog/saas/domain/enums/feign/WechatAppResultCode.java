package com.hotdog.saas.domain.enums.feign;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WechatAppResultCode {
    SUCCESS(0, "请求成功"),

    FAIL(-1, "系统繁忙"),
    INVALID_ACCESS_TOKEN(40001, "access_token无效"),
    INVALID_APPID(40013, "不合法的AppID"),
    INVALID_CREDENTIAL_TYPE(40002, "不合法的凭证类型"),
    INVALID_SECRET(40125, "不合法的secret"),
    IP_NOT_IN_WHITELIST(40164, "调用接口的IP地址不在白名单中"),
    MISSING_SECRET(41004, "缺少secret参数"),
    TOKEN_INTERFACE_FORBIDDEN(50004, "禁止使用token接口"),
    ACCOUNT_FROZEN(50007, "账号已冻结"),
    THIRD_PARTY_TOKEN_REQUIRED(61024, "第三方平台需要使用第三方平台专用token"),
    APP_SECRET_FROZEN(40243, "AppSecret已被冻结，请登录小程序平台解冻后再次调用"),

    ;


    private final Integer code;
    private final String message;

    public static WechatAppResultCode codeToEnum(Integer code) {
        for (WechatAppResultCode wechatAppResultCode : values()) {
            if (Objects.equals(wechatAppResultCode.getCode(), code)) {
                return wechatAppResultCode;
            }
        }
        return FAIL;
    }

}
