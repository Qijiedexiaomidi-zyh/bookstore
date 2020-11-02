package com.abc.bookstore.admin.login.controller;

import com.abc.bookstore.admin.login.service.LoginService;
import com.abc.bookstore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/login")
public class loginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录管理员系统
     */
    @RequestMapping("logincheck")
    public String logincheck(User user, HttpSession session){

        User login_user = loginService.logincheck(user);
        if (login_user!=null){
            if (login_user.getRole().equals("超级管理员")){
                session.setAttribute("login_user",login_user);
                return "/admin/login/home.jsp";
            }else {
                return "redirect:/admin/error/privilege.jsp";
            }
        }else {
            session.setAttribute("msg1","用户名或密码错误，请重新登陆");
            return "/admin/login/login.jsp";
        }
    }

    /**
     * 退出管理员系统
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("login_user");
        session.setAttribute("msg1","已退出，欢迎下次登陆");
        return "/admin/login/login.jsp";
    }
}
