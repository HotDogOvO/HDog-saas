package com.hotdog.saas.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    public static String APP_SECRET;

    public static String WECHAT_APP_APPID;

    public static String WECHAT_APP_SECRET;

    @Value("${project.secret}")
    public void setAppSecret(String appSecret) {
        ProjectConfig.APP_SECRET = appSecret;
    }

    @Value("${project.wechat.app.appid}")
    public void setWechatAppAppid(String wechatAppAppid) {
        ProjectConfig.WECHAT_APP_APPID = wechatAppAppid;
    }

    @Value("${project.wechat.app.secret}")
    public void setWechatAppSecret(String wechatAppSecret) {
        ProjectConfig.WECHAT_APP_SECRET = wechatAppSecret;
    }
}
