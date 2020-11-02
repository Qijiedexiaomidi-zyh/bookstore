package com.abc.bookstore.utils;

import com.abc.bookstore.commons.beans.User;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 自定义标签，相当于拦截器、过滤器
 */
public class LoginTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        PageContext context = (PageContext) this.getJspContext();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        User login_user = (User) context.getSession().getAttribute("login_user");
        if (login_user == null){
            //这个 sendRedirect 很特殊，必须加上项目路径，不加就会出错
            response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");
        }
        super.doTag();
    }
}
