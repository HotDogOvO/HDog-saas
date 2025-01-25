package com.hotdog.saas.matrix.matrix.web.config;

import com.hotdog.saas.matrix.domain.config.ProjectConfig;
import com.hotdog.saas.matrix.matrix.web.interceptor.SignInterceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web拦截器配置
 *
 * @author hotdog
 * @date 2024/12/10 17:47
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${project.valid.sign}")
    private Boolean needValidSign;

    @Value("${project.valid.token}")
    private Boolean needValidToken;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // sign校验
        registry.addInterceptor(new SignInterceptor(ProjectConfig.APP_SECRET, needValidSign))
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 匹配所有路径
                .allowedOriginPatterns("*") // 允许所有来源
                .allowedMethods("POST", "OPTIONS") // 允许的方法
                .allowedHeaders("*") // 允许的请求头
                .allowCredentials(true) // 是否允许发送Cookie
                .maxAge(3600); // 缓存时间
    }

}
