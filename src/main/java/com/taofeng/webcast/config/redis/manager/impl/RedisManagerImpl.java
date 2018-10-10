package com.taofeng.webcast.config.redis.manager.impl;

import com.taofeng.webcast.config.redis.manager.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>redis相关操作</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/1 上午10:15
 * @since V1.0
 */
@Component
public class RedisManagerImpl implements RedisManager {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     * @param key key
     * @param value value
     * @return 是否设置成功
     */
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            //操作字符串
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     * @param key key
     * @param value value
     * @param expireTime 失效时间
     * @return 是否设置成功
     */
    public boolean set(String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     * @param key key
     * @param field field
     * @param value value
     * @return 是否设置成功
     */
    public boolean setMap(String key, String field, Object value) {
        boolean result = false;
        try {
            HashOperations<Serializable, Serializable, Object> hashOperations = redisTemplate.opsForHash();
            hashOperations.put(key, field, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存
     * @param key key
     * @return
     */
    public Object get(String key) {
        Object result;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     * @param key key
     * @param field field
     * @return 是否设置成功
     */
    public Object get(String key, String field) {
        HashOperations<Serializable, Serializable, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, field);
    }

    /**
     * 批量删除对应的value
     * @param keys 要删除的Key
     */
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern 正则模式
     */
    public void removePattern(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     * @param key key
     */
    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 删除hash表中的key
     * @param key key
     * @param field field
     */
    public void remove(String key, String field) {
        HashOperations<Serializable, Serializable, Object> hashOperations = redisTemplate.opsForHash();
        if (hashOperations.hasKey(key, field)) {
            hashOperations.delete(key, field);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key key
     * @return 是否存在key
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key key
     * @return 是否存在key
     */
    public boolean exists(String key, String field) {
        HashOperations<Serializable, Serializable, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.hasKey(key, field);
    }
}
