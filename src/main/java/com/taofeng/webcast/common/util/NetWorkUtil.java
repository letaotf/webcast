package com.taofeng.webcast.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>网络连接请求</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/6 下午6:05
 * @since V1.0
 */
public class NetWorkUtil {
    public NetWorkUtil() {
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : ((ServletRequestAttributes)requestAttributes).getRequest();
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : ((ServletRequestAttributes)requestAttributes).getResponse();
    }
}
