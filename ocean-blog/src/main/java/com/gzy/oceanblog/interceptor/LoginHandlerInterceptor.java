package com.gzy.oceanblog.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute("blogUser");
        if (user == null) {
            request.setAttribute("message", "请先登录");
            // 获取request 返回页面登录
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
        // 返回true 请求会继续执行后续操作
        // 返回false 拦截器执行到此会停止
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object user = request.getSession().getAttribute("blogUser");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object user = request.getSession().getAttribute("blogUser");
    }
}
