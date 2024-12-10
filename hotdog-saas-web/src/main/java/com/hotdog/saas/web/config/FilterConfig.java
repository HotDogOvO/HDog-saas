package com.hotdog.saas.web.config;

import com.hotdog.saas.web.filter.PostRequestFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<PostRequestFilter> postRequestFilter() {
        FilterRegistrationBean<PostRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new PostRequestFilter());
        registrationBean.addUrlPatterns("/**");
        return registrationBean;
    }

}
