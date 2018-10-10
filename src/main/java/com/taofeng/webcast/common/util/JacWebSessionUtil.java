package com.taofeng.webcast.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * <p>网络会话传输</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/24 上午11:05
 * @since V1.0
 */
@Slf4j
public class JacWebSessionUtil {

    public static HttpSession getSession(){
        return NetWorkUtil.getRequest().getSession();
    }

    /**
     * 从cookier中获取sessionId
     * @Param sessionName cookier的name
     * @return sessionId 会话Id
     */
    public static String getSessionIdFromCookie(String sessionName) {
        Cookie[] cookies = NetWorkUtil.getRequest().getCookies();
        if (!Objects.isNull(cookies) && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (sessionName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return setSessionIntoCookie(null,sessionName);
    }

    /**
     * 判断cooier是否存在
     * @Param sessionName cookier的name
     * @return true 存在 false 不存在
     */
    public static boolean hasSession(String sessionName) {
        Cookie[] cookies = NetWorkUtil.getRequest().getCookies();
        if (!Objects.isNull(cookies) && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (sessionName.equals(cookie.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 向cookier中插入sessionId
     * sessionId 可为空 当为空时,会重新生成一个sessionId
     * @param sessionId
     * @Param sessionName cookier的name
     * @return sessionId
     */
    public static String setSessionIntoCookie(String sessionId,String sessionName) {
        if(Objects.isNull(sessionId)){
            sessionId = UUIDGeneratorUtil.getUUID();
        }
        Cookie cookie = new Cookie(sessionName, sessionId);
        cookie.setPath("/");
        NetWorkUtil.getResponse().addCookie(cookie);
        return sessionId;
    }

    /**
     * 删除cookier中的值
     * @Param sessionName cookier的name
     */
    public static void deleteSession(String sessionName) {
        Cookie cookie = new Cookie(sessionName, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        NetWorkUtil.getResponse().addCookie(cookie);
    }

}
