package com.demo.intarnalcommon.constant;

import lombok.Data;
import lombok.Getter;

/**
 * Created by jzx on 2022/12/16 17:24
 */

public enum CommonStatusEnum {

    VERFICATION_CODE_ERROR(1001, "验证码错误！"),
    TOKEN_ERROR(1199, "token错误！"),
    TOKEN_TIMEOUT(1198, "token过期！"),
    USER_NOT_EXISTS(2001, "当前用户不存在！"),

    SUCCESS(0,"success"),

    FAIL(1, "fail")

    ;
    @Getter
    private int code;
    @Getter
    private String value;
    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
