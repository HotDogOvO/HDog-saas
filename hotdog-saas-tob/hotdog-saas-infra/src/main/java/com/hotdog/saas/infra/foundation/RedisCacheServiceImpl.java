package com.hotdog.saas.infra.foundation;

import com.alibaba.fastjson2.JSONObject;
import com.hotdog.saas.domain.foundation.RedisCacheService;

import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheServiceImpl implements RedisCacheService {

    private final RedissonClient redissonClient;

    public RedisCacheServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public <T> void set(String key, T value) {
        String jsonString = JSONObject.toJSONString(value);
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(jsonString);
    }

    @Override
    public <T> void set(String key, T value, Long seconds) {
        String jsonString = JSONObject.toJSONString(value);
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(jsonString, seconds, TimeUnit.SECONDS);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        return JSONObject.parseObject(bucket.get(), clazz);
    }

    @Override
    public void delete(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.delete();
    }

    @Override
    public RLock getLock(String lockName) {
        return redissonClient.getLock(lockName);
    }

}
