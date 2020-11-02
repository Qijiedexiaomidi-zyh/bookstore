package com.abc.bookstore.client.user.service;

import com.abc.bookstore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    int addUser(User user, HttpServletRequest request);

    int activeUser(String activeCode);

    User findEmail(String email);

    User findUsername(String username);

    User login(User user);

    int modifyUser(User user);

}
