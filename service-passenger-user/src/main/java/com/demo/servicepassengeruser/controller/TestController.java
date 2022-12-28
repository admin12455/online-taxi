package com.demo.servicepassengeruser.controller;

import com.demo.intarnalcommon.dto.ResponseResurt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jzx on 2022/12/26 20:21
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseResurt test() {
        return ResponseResurt.success();
    }
}
