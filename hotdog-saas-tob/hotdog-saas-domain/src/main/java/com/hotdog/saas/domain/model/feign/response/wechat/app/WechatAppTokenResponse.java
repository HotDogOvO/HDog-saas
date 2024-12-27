package com.hotdog.saas.domain.model.feign.response.wechat.app;

import com.hotdog.saas.domain.model.feign.response.wechat.BaseWechatResponseParam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WechatAppTokenResponse extends BaseWechatResponseParam {

    private String accessToken;

    private Long expiresIn;

}
