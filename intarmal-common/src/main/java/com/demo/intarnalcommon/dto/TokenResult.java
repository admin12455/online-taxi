package com.demo.intarnalcommon.dto;

import lombok.Data;

/**
 * Created by jzx on 2022/12/29 11:34
 */
@Data
public class TokenResult {
    private String phone;
    private String identity;
    private String tokenType;
}
