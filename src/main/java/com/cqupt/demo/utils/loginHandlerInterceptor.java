package com.cqupt.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.Admin;
import com.cqupt.demo.Bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class loginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        Admin loginAdmin=(Admin)request.getSession().getAttribute("loginAdmin");
        if (loginAdmin==null&&loginUser==null){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            JSONObject error=new JSONObject();
            error.put("Msg","用户未登录");
            PrintWriter writer = response.getWriter();
            writer.append(error.toString());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
