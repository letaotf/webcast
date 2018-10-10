package com.taofeng.webcast.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * <p>redis缓存配置</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/1 上午10:15
 * @since V1.0
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String hostName;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.pool.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.redis.default.expire-seconds:3600}")
    private long defaultExpireTime;

    /**
     * cache key的生成策略
     * @return cache key的生成器
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (tar, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(tar.getClass().getName());
            sb.append(method.getName());
            for (Object param : params) {
                sb.append(param.toString());
            }
            return sb.toString();
        };
    }

    /**
     * jedis 连接池配置
     * @return jedis 连接池配置
     */
    @Bean
    public JedisPoolConfig getRedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        //最大空闲连接数
        jedisPoolConfig.setMaxIdle(maxIdle);
        //获取连接时的最大等待毫秒数
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    /**
     * jedis 连接工厂配置
     * @return jedis 连接工厂配置
     */
    @Bean
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(getRedisPoolConfig());
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setTimeout(timeout);
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = getRedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, hostName, port, timeout, password);
        return jedisPool;
    }

    /**
     * redis的操作模板
     * @return redis的操作模板
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(getConnectionFactory());
        // 这里设置完了之后就不用再实现Serializable接口
        setSerializer(redisTemplate);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 设置序列化
     * @param stringRedisTemplate string redis模板
     */
    private void setSerializer(StringRedisTemplate stringRedisTemplate) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 防止disable SerializationFeature.FAIL_ON_EMPTY_BEANS的报错
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        stringRedisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置默认缓存过期时间(秒)
        rcm.setDefaultExpiration(defaultExpireTime);
        return rcm;
    }

}
