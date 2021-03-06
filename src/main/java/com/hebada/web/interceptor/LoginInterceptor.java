package com.hebada.web.interceptor;

import com.hebada.web.ControllerHelper;
import com.hebada.web.WebConfig;
import com.hebada.web.exception.NoAuthorizedExeption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by paddy.luo on 2017/9/19.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginRequired loginRequired = ControllerHelper.findMethodOrClassLevelAnnotation(handler, LoginRequired.class);
        if (loginRequired == null || !loginRequired.required()) return true;
        Object isLogin = request.getSession().getAttribute(WebConfig.LOGIN_USER);
        if (isLogin == null)
            throw new NoAuthorizedExeption();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
