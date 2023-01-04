package com.demo.apipassenger.remote;

import com.demo.intarnalcommon.dto.PassengerUser;
import com.demo.intarnalcommon.dto.ResponseResurt;
import com.demo.intarnalcommon.request.VerificationCodeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jzx on 2022/12/28 17:40
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    ResponseResurt loginOrRegister(@RequestBody VerificationCodeDto verificationCodeDto);

    @RequestMapping(method = RequestMethod.GET, value = "/user/{phone}")
    ResponseResurt<PassengerUser> getUserByPhone(@PathVariable(value = "phone") String phone);
}
