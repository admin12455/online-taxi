package com.demo.apipassenger.controller;

import com.demo.apipassenger.service.UserService;
import com.demo.intarnalcommon.dto.ResponseResurt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jzx on 2023/1/3 16:25
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseResurt getUser(HttpServletRequest request) {
        //response 获取token
        String token = request.getHeader("Authorization");
        return userService.getUser(token);
    }
}
