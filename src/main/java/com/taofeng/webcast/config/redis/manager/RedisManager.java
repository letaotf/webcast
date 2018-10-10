package com.taofeng.webcast.config.redis.manager;

/**
 * <p>redis相关操作</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/1 上午10:15
 * @since V1.0
 */
public interface RedisManager {

    /**
     * 写入缓存
     * @param key key
     * @param value value
     * @return 是否设置成功
     */
    boolean set(String key, Object value);
    
    /**
     * 写入缓存
     * @param key key
     * @param value value
     * @param expireTime 失效时间
     * @return 是否设置成功
     */
    boolean set(String key, Object value, Long expireTime);

    /**
     * 写入缓存
     * @param key key
     * @param field field
     * @param value value
     * @return 是否设置成功
     */
    boolean setMap(String key, String field, Object value);

    /**
     * 读取缓存
     * @param key key
     * @return
     */
    Object get(String key);

    /**
     * 写入缓存
     * @param key key
     * @param field field
     * @return 是否设置成功
     */
    Object get(String key, String field);

    /**
     * 批量删除对应的value
     * @param keys 要删除的Key
     */
    void remove(String... keys);

    /**
     * 批量删除key
     * @param pattern 正则模式
     */
    void removePattern(String pattern);

    /**
     * 删除对应的value
     * @param key key
     */
    void remove(String key);

    /**
     * 删除hash表中的key
     * @param key key
     * @param field field
     */
    void remove(String key, String field);

    /**
     * 判断缓存中是否有对应的value
     * @param key key
     * @return 是否存在key
     */
    boolean exists(String key);

    /**
     * 判断缓存中是否有对应的value
     * @param key key
     * @return 是否存在key
     */
    boolean exists(String key, String field);
}
