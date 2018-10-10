package com.taofeng.webcast.config.session.manager;

/**
 * <p>提供的sessionid获取用户信息</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/20 下午7:22
 * @since V1.0
 */
public interface SessionDoer {

    ThreadLocal<Session> threadLocalSession = new ThreadLocal<>();

    /**
     * 根据sessionId获取用户id
     * @param sessionId 会话ID
     * @return 用户Id
     */
    Long getUserId(String sessionId);

    /**
     * 根据sessionId获取登录名称
     * @param sessionId 会话ID
     * @return 用户名称
     */
    String getLoginName(String sessionId);

    /**
     * 根据session获取人员信息,在通过key获得相应的值
     * @param sessionId
     * @param key attrs中的值
     * @return  key 所对应的信息
     */
    Object getAttribute(String sessionId, String key);

    /**
     * 根据session,得到用户id
     * @param session 会话对象
     * @return 用户ID
     */
    Long getUserId(Session session);

    /**
     * 根据session,得到登录名
     * @param session 会话对象
     * @return 用户名称
     */
    String getLoginName(Session session);

    /**
     * 根据session,得到登录名,在通过key获得相应的值
     * @param session 会话ID
     * @param key attrs中的值
     * @return 所对应的信息
     */
    Object getAttribute(Session session, String key);
}
