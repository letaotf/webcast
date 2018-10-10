package com.taofeng.webcast.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
/**
 * <p>redis</p >锁
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/1 上午10:15
 * @since V1.0
 */
@Slf4j
@Component
public class RedisLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    private static JedisPool jedisPool;

    /***
     * spring注入赋值静态变量
     */
    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        RedisLock.jedisPool = jedisPool;
    }

    private static Jedis getJedis() {
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            log.error("从连接池获取redis实例失败", e);
        }
        return null;
    }

    private static void close(Jedis jedis) {
        try {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        } catch (Exception e) {
            log.error("redis实例放回连接池异常", e);
        }
    }

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {

        Jedis jedis = getJedis();
        try {
            String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        } catch (Exception e){
            log.error(String.format("redis分布式锁%s,s%获取失败", lockKey, requestId), e);
            return false;
        } finally {
            close(jedis);
        }


    }

    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(String lockKey, String requestId) {

        Jedis jedis = getJedis();
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        } catch (Exception e){
            log.error(String.format("redis分布式锁%s,s%释放失败", lockKey, requestId), e);
            return false;
        } finally {
            close(jedis);
        }
    }

}
