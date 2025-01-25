package com.hotdog.saas.matrix.web.filter;

import com.hotdog.saas.domain.utils.HttpUtils;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Post请求拦截器，用于缓存@RequestBody
 * @author hotdog
 * @date 2024/12/10 18:04
 */
public class PostRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (HttpUtils.validHttpMethod(request.getMethod())) {
            HttpServletRequest cachedRequest = new PostRequestWrapper(request);
            filterChain.doFilter(cachedRequest, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

}

