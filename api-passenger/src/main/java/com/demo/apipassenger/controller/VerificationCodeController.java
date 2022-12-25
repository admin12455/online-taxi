package com.demo.apipassenger.controller;

import com.demo.apipassenger.request.VerificationCodeDto;
import com.demo.apipassenger.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jzx on 2022/12/16 15:46
 */
@RestController
public class VerificationCodeController {

    @Autowired
    VerificationService verificationService;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDto verificationCodeDto) {
        String passengerPhone = verificationCodeDto.getPassengerPhone();
        System.out.println("参数：" + passengerPhone);
        return verificationService.generatorCode(passengerPhone);
    }
}
