package com.liwell.cinema.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2024/4/27
 */
public class SessionUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    private static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static String getSessionAttribute(String key) {
        return getSession().getAttribute(key).toString();
    }

    public static void setSessionAttribute(String key, String value) {
        getSession().setAttribute(key, value);
    }

}
