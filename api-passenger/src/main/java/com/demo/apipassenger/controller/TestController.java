package com.demo.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jzx on 2022/12/16 15:38
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test secuss";
    }
}
