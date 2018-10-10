package com.taofeng.webcast.config.session.manager;

import java.util.HashMap;

/**
 * <p>session相关的操作接口</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/20 下午7:22
 * @since V1.0
 */
public interface SessionHolder {

    /**
     * 创建session，id可为空，则自动创建sessinId
     * @param id
     * @return
     */
    Session createSession(String id, HashMap<String, Object> attrs);

    /**
     * 替换sessionId与userId
     * 在Redis中的关系
     * @param sessionId 会话Id
     * @param userId 用户Id
     * @param prefix 前缀 例：JAC_USER_SESSION
     * @return True 成功 false 失败
     */
    boolean replaceSession(String sessionId, String userId, String prefix);

    /**
     * 保存session到redis中
     * @param session 会话
     * @return True 成功 False 失败
     */
    boolean saveSessionToRedis(Session session);

    /**
     * 根据用户ID获取sessionId
     * @param userId 用户Id
     * @param prefix 前缀 例:JAC_USER_SESSION_
     * @return 会话ID
     */
    String getSessionIdByUserId(Long userId, String prefix);

    /**
     * 根据sessionId
     * 从redis中读取session
     * @param sessionId 会话ID
     * @return 会话对象
     */
    Session getSessionFromRedis(String sessionId);

    /**
     * 根据sessionId
     * 从redis中删除session
     * @param sessionId 会话ID
     */
    void removeSessionFromRedis(String sessionId);

    /**
     * * 根据userId
     * 从redis中读取session
     * @param userId 用户Id
     * @param prefix 例：JAC_USER_SESSION
     * @return 会话对象
     */
    Session getSessionFromRedis(Long userId, String prefix);

    /**
     * 根据userId
     * 从redis中删除session
     * @param userId
     * @param prefix 例：JAC_USER_SESSION
     */
    void removeSessionFromRedis(Long userId, String prefix);
}
