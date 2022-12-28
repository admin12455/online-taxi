package com.demo.intarnalcommon.request;

import lombok.Data;

/**
 * Created by jzx on 2022/12/16 15:48
 */
@Data
public class VerificationCodeDto {
    private String passengerPhone;

    private String verificationCode;
}
