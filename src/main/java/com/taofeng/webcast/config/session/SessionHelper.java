/**
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @Package com.mhc.hummer.core.session
 * @author 星璇（xingxuan@maihaoche.com）
 * @date 2018年03月08日 12:02
 * @Copyright 2017-2020 www.maihaoche.com Inc. All rights reserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.taofeng.webcast.config.session;

import com.taofeng.webcast.common.DTO.UserSessionDTO;
import com.taofeng.webcast.common.constant.CookieNameConstant;
import com.taofeng.webcast.common.util.JacWebSessionUtil;
import com.taofeng.webcast.config.session.manager.SessionDoer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>PC端Session操作类</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/20 下午7:22
 * @since V1.0
 */
@Component
public class SessionHelper {

    @Autowired
    private SessionDoer sessionDoer;

    /**
     * 从session获取用户ID
     * @return 用户ID
     */
    public Long getUserId() {
        String sessionId = JacWebSessionUtil.getSessionIdFromCookie(CookieNameConstant.WEB_SESSION_ID);
        return sessionDoer.getUserId(sessionId);
    }

    /**
     * session获取用户名称
     * @return 用户名称
     */
    public String getUserName() {
        String sessionId = JacWebSessionUtil.getSessionIdFromCookie(CookieNameConstant.WEB_SESSION_ID);
        return sessionDoer.getLoginName(sessionId);
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    public UserSessionDTO getUserInfo(){
        String sessionId = JacWebSessionUtil.getSessionIdFromCookie(CookieNameConstant.WEB_SESSION_ID);
        UserSessionDTO userSessionBO = new UserSessionDTO();
        userSessionBO.setUserId(sessionDoer.getUserId(sessionId));
        userSessionBO.setUserName(sessionDoer.getLoginName(sessionId));
        return userSessionBO;
    }
}
