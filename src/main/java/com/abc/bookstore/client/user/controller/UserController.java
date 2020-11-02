package com.abc.bookstore.client.user.controller;

import com.abc.bookstore.client.user.service.UserService;
import com.abc.bookstore.commons.beans.User;
import com.abc.bookstore.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/client/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册账号
     */
    @RequestMapping("/register")
    public String register(User user, String checkCode, HttpSession session,HttpServletRequest request){

        //1、获取唯一的注册码
        user.setActiveCode(IdUtils.getUUID());
        //1、先判断校验码输入是否正确
        String checkcode_session = (String) session.getAttribute("checkcode_session");
        if (checkcode_session.equals(checkCode)){
            int rows = userService.addUser(user,request);
            if (rows>0){
                //redirect之后就不需要修改jsp页面的路径了
                return "redirect:/client/registersuccess.jsp";
            }else {
                session.setAttribute("msg1","注册失败，请重新注册");
                return "/client/register.jsp";
            }
        }else {
            session.setAttribute("msg2","输入的校验码不正确，请重新输入");
            return "/client/register.jsp";
        }
    }

    /**
     * 获取激活码，修改激活状态
     */
    @RequestMapping("/activeUser")
    public String activeUser(String activeCode){
        System.out.println("激活码："+activeCode);
        int rows = userService.activeUser(activeCode);
        if (rows>0){
            return "redirect:/client/activesuccess.jsp";
        }else {
            return "/client/activeError.jsp";
        }
    }

    /**
     * 验证邮箱号是否已存在
     */
    @ResponseBody
    @RequestMapping("/findEmail")
    public String findEmail(String email){
        System.out.println("email："+email);
        User user = userService.findEmail(email);
        if (user != null){
            return "exists";
        }else {
            return "not-exists";
        }
    }

    /**
     * 验证会员名是否已存在
     */
    @ResponseBody
    @RequestMapping("/findUsername")
    public String findUsername(String username){
        System.out.println("email："+username);
        User user = userService.findUsername(username);
        if (user != null){
            return "exists";
        }else {
            return "not-exists";
        }
    }

    /**
     * 判断用户是否已登录
     */
    @RequestMapping("/goto_login")
    public String goto_login(HttpServletRequest request,HttpSession session){
        //判断是否已经登录
        User login_user = (User) session.getAttribute("login_user");
        if (login_user != null){
            return "/client/myAccount.jsp";
        }else {
            //判断是否选择了自动登录按钮
            login_user = autologin(request);
            if (login_user != null){
                session.setAttribute("login_user",login_user);
                return "/client/myAccount.jsp";
            }else {
                return "/client/login.jsp";
            }
        }
    }

    /**
     * 自动登录
     */
    private User autologin(HttpServletRequest request) {
        String username = null;
        String password = null;
        //1、获取cookie中的值
        Cookie[] cookies = request.getCookies();
        //2、遍历cookie中的值
        for (Cookie cookie:cookies){
            if ("bookstore_username".equals(cookie.getName())){
                username = cookie.getValue();
            }
            if ("bookstore_password".equals(cookie.getName())){
                password = cookie.getValue();
            }
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return userService.login(user);
    }

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response,String remember,String autologin, HttpSession session){

        User login_user = userService.login(user);
        if (login_user!=null){
            if (login_user.getState()==1){
                if ("1".equals(autologin)){
                    //自动登录，就是把用户名和密码都保存到cookie
                    addCookie(autologin,user,request,response);
                }else if ("1".equals(remember)){
                    //记住用户名，就是把用户名保存到cookie
                    addCookie(autologin,user,request,response);
                }
                session.setAttribute("login_user",login_user);
                return "/client/myAccount.jsp";
            }else {
                session.setAttribute("msg3","该用户还未激活，请激活后再登录");
                return "/client/login.jsp";
            }
        }else {
            session.setAttribute("msg3","输入会员名或者密码错误，请重新输入");
            return "/client/login.jsp";
        }
    }

    /**
     * 将登录名、密码添加至cookie
     */
    private void addCookie(String autologin, User user, HttpServletRequest request, HttpServletResponse response) {
        //1、创建cookie对象
        Cookie cookie1 = new Cookie("bookstore_username",user.getUsername());
        //2、设置保存时间，3天
        cookie1.setMaxAge(60*60*24*3);
        //3、设置cookie的作用路径，当前项目下都试用
        cookie1.setPath(request.getContextPath()+"/");
        //4、把定义好的cookie响应到客户端
        response.addCookie(cookie1);
        if ("1".equals(autologin)){
            //1、创建cookie对象
            Cookie cookie2 = new Cookie("bookstore_password",user.getPassword());
            //2、设置保存时间，3天
            cookie2.setMaxAge(60*60*24*3);
            //3、设置cookie的作用路径，当前项目下都试用
            cookie2.setPath(request.getContextPath()+"/");
            //4、把定义好的cookie响应到客户端
            response.addCookie(cookie2);
        }
    }

    /**
     * 用户退出
     */
    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request,HttpServletResponse response,HttpSession session){

        session.removeAttribute("login_user");
        session.setAttribute("msg3","用户成功退出");
        //用户退出后，应当删除cookie
        Cookie cookie1 = new Cookie("bookstore_username",null);
        cookie1.setMaxAge(0);
        cookie1.setPath(request.getContextPath()+"/");
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("bookstore_password",null);
        cookie1.setMaxAge(0);
        cookie1.setPath(request.getContextPath()+"/");
        response.addCookie(cookie2);

        return "/client/login.jsp";
    }

    /**
     * 用户修改
     */
    @RequestMapping("/modifyUser")
    public String modifyUser(User user,HttpSession session){
        User login_user = (User) session.getAttribute("login_user");
        user.setId(login_user.getId());
        int rows = userService.modifyUser(user);
        if (rows > 0){
            session.setAttribute("msg3","用户修改信息成功，请重新登陆");
            return "/client/login.jsp";
        }else {
            session.setAttribute("msg4","用户修改信息失败，请重新修改");
            return "/client/modifyuserinfo.jsp";
        }
    }
}
