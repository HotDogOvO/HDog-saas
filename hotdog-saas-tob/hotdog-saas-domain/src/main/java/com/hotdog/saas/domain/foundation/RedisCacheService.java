package com.hotdog.saas.domain.foundation;

import org.redisson.api.RLock;

public interface RedisCacheService {

    /**
     * <p>插入redis</p>
     * <li>string结构</li>
     *
     * @param key   key
     * @param value 值
     */
    <T> void set(String key, T value);

    /**
     * <p>插入redis</p>
     * <li>string结构</li>
     *
     * @param key     key
     * @param value   值
     * @param seconds 存储时间戳
     */
    <T> void set(String key, T value, Long seconds);

    /**
     * <p>查询redis</p>
     * <li>string结构</li>
     *
     * @param key   key
     * @param clazz 类型
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 删除redis值
     * <li>string结构</li>
     *
     * @param key key
     */
    void delete(String key);

    /**
     * 获取分布式锁
     *
     * @param lockName 锁名
     * @return 锁
     */
    RLock getLock(String lockName);

}
