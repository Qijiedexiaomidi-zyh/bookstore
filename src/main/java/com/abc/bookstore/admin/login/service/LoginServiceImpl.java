package com.abc.bookstore.admin.login.service;

import com.abc.bookstore.admin.login.dao.LoginDao;
import com.abc.bookstore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public User logincheck(User user) {

        return loginDao.logincheck(user);
    }
}
