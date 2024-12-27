package com.hotdog.saas.domain.model.feign.response.wechat.app;

import com.hotdog.saas.domain.model.feign.response.wechat.BaseWechatResponseParam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WechatAppLoginResponse extends BaseWechatResponseParam {

    private String sessionKey;

    private String unionid;

    private String openid;

}
