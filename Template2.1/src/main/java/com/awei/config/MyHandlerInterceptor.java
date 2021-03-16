package com.awei.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {

    //对未登录的用户进行拦截

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取Session
        Object loginUser = request.getSession().getAttribute("username");
        //如果不存在  则未登录  无法访问
        if (loginUser == null){
            request.setAttribute("msg","没有权限，请先登录！");
            request.getRequestDispatcher("/view/login.html").forward(request,response);
            return false;
        }
        //如果存在就放行
        return true;
    }
}
