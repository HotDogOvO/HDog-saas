package com.hotdog.saas.web.config;

import com.hotdog.saas.domain.config.ProjectConfig;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.foundation.AuthService;
import com.hotdog.saas.web.interceptor.SignInterceptor;
import com.hotdog.saas.web.interceptor.TokenInterceptor;

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

    private final AuthService authService;

    public WebConfig(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // sign校验
        registry.addInterceptor(new SignInterceptor(ProjectConfig.APP_SECRET, needValidSign))
                .addPathPatterns("/**");

        // token校验
        registry.addInterceptor(new TokenInterceptor(needValidToken, authService))
                .addPathPatterns("/**")
                .excludePathPatterns(Constants.TOKEN_EXCLUDE_PATTERN_URL);
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
