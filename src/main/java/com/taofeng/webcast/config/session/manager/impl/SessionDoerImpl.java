package com.taofeng.webcast.config.session.manager.impl;


import com.taofeng.webcast.common.constant.SessionConstants;
import com.taofeng.webcast.config.session.manager.Session;
import com.taofeng.webcast.config.session.manager.SessionDoer;
import com.taofeng.webcast.config.session.manager.SessionHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>提供的sessionid获取用户信息</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/20 下午7:22
 * @since V1.0
 */
@Service
@Slf4j
public class SessionDoerImpl implements SessionDoer,Serializable {

    @Autowired
    private SessionHolder sessionHolder;

    /**
     * 根据sessionId获取用户id
     * @param sessionId 会话ID
     * @return 用户Id
     */
    @Override
    public Long getUserId(String sessionId) {
        Session session = sessionHolder.getSessionFromRedis(sessionId);
        //判断session是否为空
        if(Objects.isNull(session)) {
            return null;
        }
        String str = convertObj2String(getAttribute(session, SessionConstants.USER_ID));
        if(Objects.isNull(str)){
            return null;
        }
        return Long.valueOf(str);
    }

    /**
     * 根据sessionId获取登录名称
     * @param sessionId 会话ID
     * @return 用户名称
     */
    @Override
    public String getLoginName(String sessionId) {
        Session session = sessionHolder.getSessionFromRedis(sessionId);
        if(Objects.isNull(session)) {
            return null;
        }
        return convertObj2String(getLoginName(session));
    }

    /**
     * 根据session获取人员信息,在通过key获得相应的值
     * @param sessionId
     * @param key attrs中的值
     * @return  key 所对应的信息
     */
    @Override
    public Object getAttribute(String sessionId, String key) {
        Session session = sessionHolder.getSessionFromRedis(sessionId);
        if(Objects.isNull(session)){
            return null;
        }
        return getAttribute(session, key);
    }

    /**
     * 根据session,得到用户id
     * @param session 会话对象
     * @return 用户ID
     */
    @Override
    public Long getUserId(Session session) {
        if(Objects.isNull(session)){
            return null;
        }
        String str = convertObj2String(getAttribute(session, SessionConstants.USER_ID));
        if(Objects.isNull(str)){
            return null;
        }
        return Long.valueOf(str);
    }

    /**
     * 根据session,得到登录名
     * @param session 会话对象
     * @return 用户名称
     */
    @Override
    public String getLoginName(Session session) {
        if(Objects.isNull(session)){
            return null;
        }
        return convertObj2String(getAttribute(session, SessionConstants.LOGIN_NAME));
    }

    /**
     * 根据session,得到登录名,在通过key获得相应的值
     * @param session 会话ID
     * @param key attrs中的值 Map
     * @return 所对应的信息
     */
    @Override
    public Object getAttribute(Session session, String key) {
        if(Objects.isNull(session)) {
            return null;
        }
        if (session.existAttribute(key)) {
            return session.getAttribute(key);
        }
        return null;
    }

    /**
     * obj转string
     * @param obj
     * @return
     */
    private String convertObj2String(Object obj){
        if(Objects.isNull(obj)){
            return  "";
        }
        return  obj.toString();
    }
}
