package com.ly.account.controller;

import com.ly.account.entity.User;
import com.ly.account.service.UserService;
import common.base.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseResult<User> register(@RequestBody User user){
        return ResponseResult.success(userService.register(user));
    }

}
