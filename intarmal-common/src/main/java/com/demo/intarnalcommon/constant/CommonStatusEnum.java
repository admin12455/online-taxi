package com.demo.intarnalcommon.constant;

import lombok.Data;
import lombok.Getter;

/**
 * Created by jzx on 2022/12/16 17:24
 */

public enum CommonStatusEnum {

    VERFICATION_CODE_ERROR(1001, "验证码错误！"),

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
