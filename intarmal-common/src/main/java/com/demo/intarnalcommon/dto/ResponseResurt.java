package com.demo.intarnalcommon.dto;

import com.demo.intarnalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by jzx on 2022/12/16 17:28
 */
@Data
@Accessors(chain = true)
public class ResponseResurt<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ResponseResurt success() {
        return new ResponseResurt().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData("");
    }

    public static <T> ResponseResurt success(T data) {
        return new ResponseResurt().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    public static <T> ResponseResurt fail() {
        return new ResponseResurt().setCode(CommonStatusEnum.FAIL.getCode()).setMessage(CommonStatusEnum.FAIL.getValue());
    }

    public static <T> ResponseResurt fail(T data) {
        return new ResponseResurt().setCode(CommonStatusEnum.FAIL.getCode()).setMessage(CommonStatusEnum.FAIL.getValue()).setData(data);
    }

    public static <T> ResponseResurt fail(String message) {
        return new ResponseResurt().setCode(CommonStatusEnum.FAIL.getCode()).setMessage(message);
    }

    public static <T> ResponseResurt fail(CommonStatusEnum commonStatusEnum) {
        return new ResponseResurt().setCode(commonStatusEnum.getCode()).setMessage(commonStatusEnum.getValue());
    }

    public static <T> ResponseResurt fail(int code, String message) {
        return new ResponseResurt().setCode(code).setMessage(message);
    }

    public static <T> ResponseResurt fail(int code, String message, String data) {
        return new ResponseResurt().setCode(code).setMessage(message).setData(data);
    }
}
