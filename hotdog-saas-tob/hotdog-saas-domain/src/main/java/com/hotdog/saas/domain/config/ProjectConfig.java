package com.hotdog.saas.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    public static String APP_SECRET;

    @Value("${project.secret}")
    public void setAppSecret(String appSecret) {
        ProjectConfig.APP_SECRET = appSecret;
    }

}
