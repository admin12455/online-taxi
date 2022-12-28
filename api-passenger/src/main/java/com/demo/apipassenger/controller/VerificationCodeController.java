package com.demo.apipassenger.controller;

import com.demo.intarnalcommon.request.VerificationCodeDto;
import com.demo.apipassenger.service.VerificationService;
import com.demo.intarnalcommon.dto.ResponseResurt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseResurt verificationCode(@RequestBody VerificationCodeDto verificationCodeDto) {
        String passengerPhone = verificationCodeDto.getPassengerPhone();
        return verificationService.generatorCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResurt CheckVerificationCode(@RequestBody VerificationCodeDto verificationCodeDto) {

        String passengerPhone = verificationCodeDto.getPassengerPhone();
        String verificationCode = verificationCodeDto.getVerificationCode();
        System.err.println("shoujihao :" + passengerPhone + "code:" + verificationCode);

        return verificationService.CheckVerificationCode(passengerPhone, verificationCode);
    }
}
