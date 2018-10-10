package com.taofeng.webcast.config;


import com.taofeng.webcast.common.constant.CookieNameConstant;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.util.GsonUtils;
import com.taofeng.webcast.common.util.JacWebSessionUtil;
import com.taofeng.webcast.config.session.manager.SessionDoer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>浏览器请求拦截</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/24 上午11:45
 * @since V1.0
 */
@Slf4j
public class JacSimpleInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SessionDoer sessionDoer;

    private static final String JAC_SERVLET_PATH = "/";

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getServletPath().endsWith(".jsonHtml")) {
            return true;
        }
        if(isErrorPage(request)) {
            String sessionId = JacWebSessionUtil.getSessionIdFromCookie(CookieNameConstant.WEB_SESSION_ID);
            Long userId = sessionDoer.getUserId(sessionId);
            if (userId == null) {
                //操作类的接口不拦截
                if (request.getServletPath().endsWith(".json")) {
                    //异常都返回json数据
                    writeBizResult(response, GeneralResult.error("", "用户登录session已过期，请重新登录"));
                    return false;
                }
                log.info("sessionId对应的用户不存在,sessionId:{}", sessionId);
                //response.sendRedirect("/common.htm");
                //return false;
            }
            return true;
        }else{
            String sessionId = JacWebSessionUtil.getSessionIdFromCookie(CookieNameConstant.WEB_SESSION_ID);
            Long userId = sessionDoer.getUserId(sessionId);
            if (userId == null){
                if (request.getServletPath().endsWith(".json")) {
                    //异常都返回json数据
                    writeBizResult(response, GeneralResult.error("", "用户登录session已过期，请重新登录"));
                    return false;
                }
            }
            return true;
        }
    }

    private void writeBizResult(HttpServletResponse response, GeneralResult<Object> objectBizResult) {
        writeBizResultCore(response, GsonUtils.toJsonString(objectBizResult));
    }

    private void writeBizResultCore(HttpServletResponse response, String result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            log.error("获取输出流异常", e);
        }
        out.print(result);
        out.flush();
        out.close();
    }

    private boolean isErrorPage(HttpServletRequest request) {
        return null != request &&
                StringUtils.isNotBlank(request.getServletPath()) &&
                request.getServletPath().startsWith("/error") &&
                JacWebSessionUtil.hasSession(CookieNameConstant.WEB_SESSION_ID);
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        HandlerMethod targetMethod = (HandlerMethod) handler;

        MethodParameter methodParameter = targetMethod.getReturnType();
        Class<?> paramType = methodParameter.getParameterType();
        if (paramType.equals(GeneralResult.class)) {
            return;
        }


    }

}
