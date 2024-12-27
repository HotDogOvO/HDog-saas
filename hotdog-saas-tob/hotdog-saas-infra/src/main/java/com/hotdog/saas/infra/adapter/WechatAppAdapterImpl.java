package com.hotdog.saas.infra.adapter;

import com.hotdog.saas.domain.adapter.WechatAppAdapter;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.feign.request.wechat.app.WechatAppLoginRequest;
import com.hotdog.saas.domain.model.feign.request.wechat.app.WechatAppTokenRequest;
import com.hotdog.saas.domain.model.feign.response.wechat.app.WechatAppLoginResponse;
import com.hotdog.saas.domain.model.feign.response.wechat.app.WechatAppTokenResponse;
import com.hotdog.saas.infra.feign.external.wechat.app.WechatAppClient;

import org.springframework.stereotype.Component;

@Component
public class WechatAppAdapterImpl implements WechatAppAdapter {

    private final WechatAppClient wechatAppClient;

    public WechatAppAdapterImpl(WechatAppClient wechatAppClient) {
        this.wechatAppClient = wechatAppClient;
    }

    @Override
    public WechatAppTokenResponse getAccessToken(WechatAppTokenRequest request) {
        return (WechatAppTokenResponse) wechatAppClient.request(() ->
                        wechatAppClient.getAccessToken(request.getAppid(), request.getSecret(), Constants.DEFAULT_WECHAT_TOKEN_GRANT_TYPE))
                .orElseThrow(() -> new BusinessException(ResultCodeEnum.FEIGN_CALL_FAIL));
    }

    @Override
    public WechatAppLoginResponse login(WechatAppLoginRequest request) {
        return wechatAppClient.login(request.getAppid(), request.getSecret(), request.getJsCode(), Constants.DEFAULT_WECHAT_LOGIN_GRANT_TYPE);
    }
}
