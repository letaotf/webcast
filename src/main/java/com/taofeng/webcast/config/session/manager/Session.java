package com.taofeng.webcast.config.session.manager;

import java.util.HashMap;

/**
 * <p>session的基础数据格式</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/20 下午7:22
 * @since V1.0
 */
public interface Session {

    /**
     * 设置attrs
     * @param key
     * @param value
     */
    void setAttribute(String key, Object value);

    /**
     * 根据key值获取  attrs 中的值
     * @param key
     * @return
     */
    Object getAttribute(String key);

    /**
     * 插入sessionId
     * @param sessionId
     */
    void setSessionId(String sessionId);

    /**
     * set sessionId 所对应的信息
     * @param attrs
     */
    void setAttrs(HashMap<String, Object> attrs);

    /**
     * 获取sessionId
     * @return
     */
    String getSessionId();

    /**
     * 获取sessionId对应的信息
     * @return
     */
    HashMap<String, Object> getAttrs();

    /**
     * 删除attrs 中key对应的值
     * @param key
     */
    void removeAttribute(String key);

    /**
     * 判断attrs 中是否存在相应的key值
     * @param key
     * @return
     */
    boolean existAttribute(String key);

    /**
     * 设置sessionId 的有效时间
     * @param time
     */
    void update(long time);
}