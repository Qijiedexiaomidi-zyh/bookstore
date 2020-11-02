package com.abc.bookstore.admin.login.Interceptor;

import com.abc.bookstore.commons.beans.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        //也就是登录方法不拦截
        if (url.endsWith("/logincheck")){
            return true;
        }
        User login_user = (User) request.getSession().getAttribute("login_user");
        if (login_user != null){
            if ("超级管理员".equals(login_user.getRole())){
                return true;
            }else {
                //此时，也是需要加上项目的路径才行
                response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
                return false;
            }
        }else {
            //返回到登录页面
            response.sendRedirect(request.getContextPath()+"/admin/login/login.jsp");
            return false;
        }
    }
}
