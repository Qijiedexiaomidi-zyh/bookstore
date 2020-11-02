package com.abc.bookstore.client.user.dao;

import com.abc.bookstore.commons.beans.User;
import com.abc.bookstore.utils.MailUtils;

public interface UserDao {

    int addUser(User user);

    int activeUser(String activeCode);

    User findEmail(String email);

    User findUsername(String username);

    User login(User user);

    int modifyUser(User user);
}
