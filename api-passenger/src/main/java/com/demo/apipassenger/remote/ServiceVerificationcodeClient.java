package com.demo.apipassenger.remote;

import com.demo.intarnalcommon.dto.ResponseResurt;
import com.demo.intarnalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jzx on 2022/12/25 17:52
 */
@FeignClient("verificationcode")
public interface ServiceVerificationcodeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    ResponseResurt<NumberCodeResponse> numberCode(@PathVariable("size") int size);
}
