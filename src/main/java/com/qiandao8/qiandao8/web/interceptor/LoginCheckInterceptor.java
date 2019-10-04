package com.qiandao8.qiandao8.web.interceptor;

import com.qiandao8.qiandao8.common.Const;
import com.qiandao8.qiandao8.common.annotation.RequireLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bert Q
 * ClassName : LoginCheckInterceptor
 * Description 专门用于登陆检查的拦截器
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 判断登陆逻辑
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            RequireLogin rL = hm.getMethodAnnotation(RequireLogin.class);
            if (rL != null  && request.getSession().getAttribute(Const.CURRENT_USER.name()) == null) {
                response.sendRedirect("/login.html");
                return false;
            }
        }
        return true;
    }

}
