package com.hotdog.saas.domain.model.feign.response.wechat;

import com.hotdog.saas.domain.enums.feign.WechatAppResultCode;

import java.util.Objects;

import lombok.Data;

@Data
public class BaseWechatResponseParam {

    private Integer errcode;
    private String errmsg;

    public boolean isSuccess() {
        return Objects.equals(this.errcode, WechatAppResultCode.SUCCESS.getCode());
    }
}
