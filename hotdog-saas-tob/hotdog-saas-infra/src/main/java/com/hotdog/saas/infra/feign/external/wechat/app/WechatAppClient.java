package com.hotdog.saas.infra.feign.external.wechat.app;

import com.hotdog.saas.domain.model.feign.response.wechat.app.WechatAppLoginResponse;
import com.hotdog.saas.domain.model.feign.response.wechat.app.WechatAppTokenResponse;
import com.hotdog.saas.infra.feign.config.CommonFeignConfig;
import com.hotdog.saas.infra.feign.external.wechat.BaseWechatFeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wechatAppClient", url = "https://api.weixin.qq.com", configuration = CommonFeignConfig.class)
public interface WechatAppClient extends BaseWechatFeignClient {

    @GetMapping(value = "/cgi-bin/token")
    WechatAppTokenResponse getAccessToken(@RequestParam("appid") String appId,
                                          @RequestParam("secret") String secret,
                                          @RequestParam("grant_type") String grantType);

    @GetMapping(value = "/sns/jscode2session", consumes = "application/json")
    WechatAppLoginResponse login(@RequestParam("appid") String appId,
                                 @RequestParam("appsecret") String appSecret,
                                 @RequestParam("js_code") String jsCode,
                                 @RequestParam("grant_type") String grantType);
}
