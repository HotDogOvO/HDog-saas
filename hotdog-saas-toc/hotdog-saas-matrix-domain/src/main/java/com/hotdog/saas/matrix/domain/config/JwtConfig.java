package com.hotdog.saas.matrix.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    public static String SUBJECT;

    public static String SECRET;

    public static Integer TTL;

    @Value("${project.jwt.subject}")
    public void setSubject(String subject) {
        JwtConfig.SUBJECT = subject;
    }

    @Value("${project.jwt.secret}")
    public void setSecret(String secret) {
        JwtConfig.SECRET = secret;
    }

    @Value("${project.jwt.ttl}")
    public void setTtl(Integer ttl) {
        JwtConfig.TTL = ttl;
    }
}
