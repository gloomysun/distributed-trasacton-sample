package com.ly.user.web;

import com.ly.common.base.ResponseResult;
import com.ly.user.entity.User;
import com.ly.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        userService.saveAndSendMsg(user);
        return ResponseResult.success();
    }

}
