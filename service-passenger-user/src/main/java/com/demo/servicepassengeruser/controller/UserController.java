package com.demo.servicepassengeruser.controller;

import com.demo.intarnalcommon.dto.PassengerUser;
import com.demo.intarnalcommon.dto.ResponseResurt;
import com.demo.intarnalcommon.request.VerificationCodeDto;
import com.demo.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jzx on 2022/12/26 20:34
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseResurt loginOrRegister(@RequestBody VerificationCodeDto verificationCodeDto) {
        String passengerPhone = verificationCodeDto.getPassengerPhone();
        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResurt<PassengerUser> getUserByPhone(@PathVariable(value = "phone") String phone) {
        System.out.println(phone);
        return userService.getUserByPhone(phone);
    }
}
