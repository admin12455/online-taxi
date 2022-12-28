package com.demo.servicepassengeruser.service;

import com.demo.intarnalcommon.dto.ResponseResurt;
import com.demo.servicepassengeruser.dto.PassengerUser;
import com.demo.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jzx on 2022/12/26 20:41
 */
@Service
public class UserService {

    @Autowired
    PassengerUserMapper userMapper;

    public ResponseResurt loginOrRegister(String passengerPhone) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = userMapper.selectByMap(map);
        if (passengerUsers.size() == 0) {
            //用户不存在，插入
            PassengerUser user = new PassengerUser();
            user.setPassengerName("张三");
            user.setPassengerGender((byte) 0);
            user.setPassengerPhone(passengerPhone);
            user.setState((byte) 0);
            user.setGmtModified(LocalDateTime.now());
            user.setGmtCreate(LocalDateTime.now());
            userMapper.insert(user);
        }

        return ResponseResurt.success();
    }
}
