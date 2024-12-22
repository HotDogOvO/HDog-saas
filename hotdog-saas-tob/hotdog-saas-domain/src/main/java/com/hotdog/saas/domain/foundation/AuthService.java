package com.hotdog.saas.domain.foundation;

import com.hotdog.saas.domain.model.Login;

public interface AuthService {

    /**
     * 生成token
     *
     * @param login 登录对象
     * @return token
     */
    String generateToken(Login login);

    /**
     * 验证token
     *
     * @param token token
     */
    void verifyToken(String token);

    /**
     * 解析token
     *
     * @return token
     */
    String extractToken();

    /**
     * 解析用户名
     *
     * @return username
     */
    String extractUsername();

    /**
     * 解析租户ID
     *
     * @return 租户ID
     */
    Long extractTenantId();
}
