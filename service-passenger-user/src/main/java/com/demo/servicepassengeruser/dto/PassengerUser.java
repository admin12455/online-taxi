package com.demo.servicepassengeruser.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by jzx on 2022/12/28 17:04
 */
@Data
public class PassengerUser {
    private long id;
    private String passengerPhone;
    private String passengerName;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    // 0：女，1：男
    private byte passengerGender;
    // 0 有效，1失效
    private byte state;
}
