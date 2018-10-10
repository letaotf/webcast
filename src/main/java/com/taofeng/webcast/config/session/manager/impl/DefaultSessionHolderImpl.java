package com.taofeng.webcast.config.session.manager.impl;


import com.alibaba.fastjson.JSON;
import com.taofeng.webcast.common.constant.RedisSessionConstant;
import com.taofeng.webcast.common.util.UUIDUtils;
import com.taofeng.webcast.config.redis.manager.RedisManager;
import com.taofeng.webcast.config.session.manager.Session;
import com.taofeng.webcast.config.session.manager.SessionHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * <p>session相关的操作接口</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/20 下午7:22
 * @since V1.0
 */
@Service("sessionHolder")
@Slf4j
public class DefaultSessionHolderImpl implements SessionHolder {

    @Autowired
    private RedisManager redisManager;

    /**
     * 默认超时时间,7天
     */
    private static final int DEFAULT_EXPIRE_TIME = 60 * 60 * 24 * 7;
    /**
     * 失效时间
     */
    private final int expireTime;

    public DefaultSessionHolderImpl() {
        this(DEFAULT_EXPIRE_TIME);
    }

    public DefaultSessionHolderImpl(int expire) {
        this.expireTime = expire;
    }

    protected int getExpireTime() {
        return expireTime;
    }

    /**
     * 创建sessionId可为空，则自动创建sessionId
     * @param sessionId 会话id
     * @return 会话对象
     */
    @Override
    public Session createSession(String sessionId, HashMap<String, Object> attrs) {
        try {
            if (StringUtils.isEmpty(sessionId)) {
                //创建sessionId
                sessionId = UUIDUtils.generateUUID();
            }
            //创建会话对象
            Session session = new DefaultSessionImpl(sessionId);
            //设置人员信息
            if (attrs != null) {
                session.setAttrs(attrs);
            }
            //将session转化成Json存入redis中
            if (saveSessionToRedis(session)) {
                return session;
            }
            return null;
        }
        catch (Exception e){
            log.error("创建session会话对象出现异常{}",e.getMessage());
            return null;
        }
    }

    /**
     * 替换sessionId与userId
     * 在Redis中的关系
     * @param sessionId 会话Id
     * @param userId 用户Id
     * @param prefix 前缀 例：JAC_USER_SESSION
     * @return True 成功 false 失败
     */
    @Override
    public boolean replaceSession(String sessionId, String userId,String prefix) {
        try {
            //创建session保存在redis中的key
            String userSessionKey = prefix + userId;
            //得到老的session并移除
            String oldSessionId = (String)redisManager.get(userSessionKey);
            if (StringUtils.isNotBlank(oldSessionId)) {
                //移除老的sessionKey,将session置为无效
                redisManager.remove(RedisSessionConstant.B2B_SESSION_PREFIX+ oldSessionId);
            }
            //替换为新的session
            redisManager.set(userSessionKey, sessionId);
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 保存session到redis中
     * @param session 会话
     * @return True 成功 False 失败
     */
    @Override
    public boolean saveSessionToRedis(Session session) {
        try {
            //将session序列化
            String json = serializeSession(session);
            //创建session在redis中的key
            String redisKey = RedisSessionConstant.B2B_SESSION_PREFIX + session.getSessionId();
            if (!redisManager.set(redisKey, json,new Long(DEFAULT_EXPIRE_TIME))) {
                log.error("在redis中保存session失败");
                return false;
            }
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 根据用户ID获取sessionId
     * @param userId 用户Id
     * @param prefix 前缀 例:WEB_USER_SESSION
     * @return 会话ID
     */
    @Override
    public String getSessionIdByUserId(Long userId,String prefix) {
        String userSessionKey = prefix+userId.toString();
        return redisManager.get(userSessionKey).toString();
    }

    /**
     * 根据sessionId
     * 从redis中读取session
     * @param sessionId 会话
     * @return 会话对象
     */
    @Override
    public Session getSessionFromRedis(String sessionId) {
        Session session = null;
        String prefixSessionId  = RedisSessionConstant.B2B_SESSION_PREFIX + sessionId;
        String json = (String)redisManager.get(prefixSessionId);
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            //判断以sessionId为key,是否存在redis中
            if(redisManager.exists(prefixSessionId)){
                return deSerializeSession(json);
            }
        } catch (Exception e) {
            log.error("根据sessionId,从redis中获取session异常:",e.getMessage());
        }
        return session;
    }

    /**
     * 根据sessionId
     * 从redis中删除session
     * @param sessionId 会话ID
     */
    @Override
    public void removeSessionFromRedis(String sessionId) {
        String userSessionKey = RedisSessionConstant.B2B_SESSION_PREFIX + sessionId;
        redisManager.remove(userSessionKey);
    }

    /**
     * 根据userId
     * 从redis中读取session
     * @param userId 用户Id
     * @param prefix 例：JAC_USER_SESSION
     * @return 会话对象
     */
    @Override
    public Session getSessionFromRedis(Long userId,String prefix) {
        //根据用户id获取sessionId
        String userSessionKey = prefix + userId.toString();
        String sessionId = (String)redisManager.get(userSessionKey);
        if(Objects.isNull(sessionId)){
            return null;
        }
        return getSessionFromRedis(sessionId);
    }

    /**
     * 根据userId
     * 从redis中读取session
     * @param userId 用户Id
     * @param prefix 例：JAC_USER_SESSION
     */
    @Override
    public void removeSessionFromRedis(Long userId,String prefix) {
        String userSessionKey = prefix + userId.toString();
        String sessionId = (String)redisManager.get(userSessionKey);
        if(Objects.isNull(sessionId)){
            return ;
        }
        removeSessionFromRedis(sessionId);
    }

    /**
     * 序列化session
     * @param session 会话对象
     * @return 会话对象的JSON类型
     */
    private String serializeSession(Session session) {
        if (Objects.isNull(session)) {
            return "";
        }
        String json = JSON.toJSONString(session);
        return json;
    }

    /**
     * 反序列化session
     * @param json
     * @return 会话对象
     */
    private Session deSerializeSession(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        Session session = JSON.parseObject(json, DefaultSessionImpl.class);
        return session;
    }
}
