package com.ly.account.service;

import com.ly.account.entity.User;

public interface UserService {

    User register(User user);

    User confirmRegister(User user);

    User cancelRegister(User user);

}
