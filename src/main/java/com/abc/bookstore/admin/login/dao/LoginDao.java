package com.abc.bookstore.admin.login.dao;

import com.abc.bookstore.commons.beans.User;

public interface LoginDao {

    User logincheck(User user);
}
