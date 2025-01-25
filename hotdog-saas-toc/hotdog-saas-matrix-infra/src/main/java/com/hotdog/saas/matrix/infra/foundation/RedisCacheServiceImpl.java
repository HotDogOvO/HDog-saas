package com.hotdog.saas.matrix.infra.foundation;

import com.alibaba.fastjson2.JSONObject;
import com.hotdog.saas.matrix.domain.foundation.RedisCacheService;

import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

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
        Duration duration = Duration.ofSeconds(seconds);
        bucket.set(jsonString, duration);
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

    private <T> RScoredSortedSet<T> zGetSortedSet(String key) {
        return redissonClient.getScoredSortedSet(key);
    }

    @Override
    public <T> List<T> zGet(String key, Class<T> clazz) {
        RScoredSortedSet<T> sortedSet = zGetSortedSet(key);
        Collection<T> collection = sortedSet.valueRange(0, -1);
        return collection.stream().toList();
    }

    @Override
    public <T> List<T> zGetRange(String key, Long startScore, Long endScore) {
        RScoredSortedSet<T> sortedSet = zGetSortedSet(key);
        Collection<T> collection = sortedSet.valueRange(startScore, true, endScore, true);
        return collection.stream().toList();
    }

    @Override
    public <T> void zSet(String key, T value, Long score) {
        RScoredSortedSet<T> zset = zGetSortedSet(key);
        zset.add(score, value);
    }

    @Override
    public <T> void zDelete(String key, T value) {
        RScoredSortedSet<T> sortedSet = zGetSortedSet(key);
        sortedSet.remove(value);
    }

    @Override
    public <T> void zDeleteRange(String key, Long startScore, Long endScore) {
        RScoredSortedSet<T> sortedSet = zGetSortedSet(key);
        sortedSet.removeRangeByScore(startScore, true, endScore, true);
    }

}
