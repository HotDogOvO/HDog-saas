package com.hotdog.saas.domain.adapter;

import com.hotdog.saas.domain.model.feign.request.wechat.app.WechatAppLoginRequest;
import com.hotdog.saas.domain.model.feign.request.wechat.app.WechatAppTokenRequest;
import com.hotdog.saas.domain.model.feign.response.wechat.app.WechatAppLoginResponse;
import com.hotdog.saas.domain.model.feign.response.wechat.app.WechatAppTokenResponse;

public interface WechatAppAdapter {

    WechatAppTokenResponse getAccessToken(WechatAppTokenRequest request);

    WechatAppLoginResponse login(WechatAppLoginRequest request);
}
