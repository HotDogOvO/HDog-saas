package com.hotdog.saas.web.config;

import com.hotdog.saas.web.interceptor.SignInterceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web拦截器配置
 * @author hotdog
 * @date 2024/12/10 17:47
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${project.secret}")
    private String secret;

    @Value("${project.valid.sign}")
    private Boolean needValidSign;

    private static final String[] EXCLUDE_PATTERN_URL = new String[]{

    };


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SignInterceptor(secret, needValidSign))
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATTERN_URL);
    }
}
