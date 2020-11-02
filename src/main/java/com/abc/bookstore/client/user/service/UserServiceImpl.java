package com.abc.bookstore.client.user.service;

import com.abc.bookstore.client.user.dao.UserDao;
import com.abc.bookstore.commons.beans.User;
import com.abc.bookstore.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.GeneralSecurityException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user, HttpServletRequest request) {

        String emailMsg = "感谢注册网上书城，请点击链接"+
                "<a href='http://39.97.230.224:80/"+request.getContextPath()+"/client/user/activeUser?activeCode="+user.getActiveCode()+"'>激活</a>用户后使用！";
        try {
            MailUtils.sendMail(user.getEmail(),emailMsg);
        } catch (MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        return userDao.addUser(user);
    }

    @Override
    public int activeUser(String activeCode) {
        return userDao.activeUser(activeCode);
    }

    @Override
    public User findEmail(String email) {
        return userDao.findEmail(email);
    }

    @Override
    public User findUsername(String username) {
        return userDao.findUsername(username);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public int modifyUser(User user) {
        return userDao.modifyUser(user);
    }
}
