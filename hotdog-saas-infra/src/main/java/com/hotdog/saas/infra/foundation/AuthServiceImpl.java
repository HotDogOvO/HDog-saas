package com.hotdog.saas.infra.foundation;

import com.hotdog.saas.domain.config.JwtConfig;
import com.hotdog.saas.domain.foundation.AuthService;
import com.hotdog.saas.domain.model.Login;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.util.*;

@Slf4j
@Component
public class AuthServiceImpl implements AuthService {

    private static final String USER_ID_KEY = "userId";
    private static final String TENANT_ID_KEY = "tenantId";
    private static final String USER_NAME_KEY = "username";
    private static final String NICK_NAME_KEY = "nickname";

    /**
     * 生成JWT token
     *
     * @param login 登录对象
     * @return token
     */
    @Override
    public String generateToken(Login login) {
        HashMap<String, Object> payLoad = new HashMap<>(8);
        payLoad.put(USER_ID_KEY, login.getId());
        payLoad.put(TENANT_ID_KEY, login.getTenantId());
        payLoad.put(USER_NAME_KEY, login.getUsername());
        payLoad.put(NICK_NAME_KEY, login.getNickname());

        return Jwts.builder()
                .subject(JwtConfig.SUBJECT)
                .claims(payLoad)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (JwtConfig.TTL * 1000)))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public void verifyToken(String token) {
        getClaims(token);
    }

    @Override
    public String extractUsername(String token) {
        return (String) getClaims(token).get(USER_NAME_KEY);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token).getPayload();
    }

    /**
     * 获取私钥
     *
     * @return javax.crypto.SecretKey
     */
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(JwtConfig.SECRET.getBytes());
    }

}
