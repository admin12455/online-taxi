package com.demo.intarnalcommon.constant;

import lombok.Data;
import lombok.Getter;

/**
 * Created by jzx on 2022/12/16 17:24
 */

public enum CommonStatusEnum {

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
