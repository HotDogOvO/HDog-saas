package com.hotdog.saas.domain.model.feign.request.wechat.app;

import com.hotdog.saas.domain.model.feign.request.wechat.BaseWechatRequestParam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WechatAppLoginRequest extends BaseWechatRequestParam {

    private String appid;

    private String secret;

    private String jsCode;

}
