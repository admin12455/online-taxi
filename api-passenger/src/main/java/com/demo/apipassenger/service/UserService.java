package com.demo.apipassenger.service;

import com.demo.apipassenger.remote.ServicePassengerUserClient;
import com.demo.intarnalcommon.constant.CommonStatusEnum;
import com.demo.intarnalcommon.dto.PassengerUser;
import com.demo.intarnalcommon.dto.ResponseResurt;
import com.demo.intarnalcommon.dto.TokenResult;
import com.demo.intarnalcommon.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jzx on 2023/1/3 16:30
 */
@Service
public class UserService {

    @Autowired
    ServicePassengerUserClient passengerUserClient;

    public ResponseResurt getUser(String accessToken) {
        //从token中获取手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        if (tokenResult== null){
            return ResponseResurt.fail("Token 无效");
        }
        String phone = tokenResult.getPhone();
        //根据手机号获取用户信息
        ResponseResurt<PassengerUser> userResponse = passengerUserClient.getUserByPhone(phone);
        if (userResponse.getCode() != CommonStatusEnum.SUCCESS.getCode()){
            return ResponseResurt.fail(userResponse.getMessage());
        } else {
            return ResponseResurt.success(userResponse.getData());
        }
    }
}
