package com.hotdog.saas.domain.constant;

public class RedisConstants {

    private static final String DEFAULT_KEY_PREFIX = "hotdog:saas:";

    private static final String USER_PREFIX = DEFAULT_KEY_PREFIX + "user:";

    public static String getUserKey(String token){
        return USER_PREFIX + token;
    }
}
