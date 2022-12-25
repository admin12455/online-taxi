package com.demo.verificationcode.controller;

import com.demo.intarnalcommon.dto.ResponseResurt;
import com.demo.intarnalcommon.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jzx on 2022/12/16 17:03
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResurt numberCode (@PathVariable("size") int size) {
        System.out.println("size:"+size);
        //生成验证码
        int code = (int) ((Math.random() * 9 + 1) * (Math.pow(10, size - 1)));

        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(code);
        return ResponseResurt.success(response);
    }

}
