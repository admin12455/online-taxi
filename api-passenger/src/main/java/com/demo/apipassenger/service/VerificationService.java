package com.demo.apipassenger.service;

import com.demo.apipassenger.remote.ServiceVerificationcodeClient;
import com.demo.intarnalcommon.dto.ResponseResurt;
import com.demo.intarnalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jzx on 2022/12/16 15:51
 */
@Service
public class VerificationService {

    @Autowired
    private ServiceVerificationcodeClient verificationcodeClient;

    //验证码前缀
    private static String verificationCodePrefix = "passage-verification-code-";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public ResponseResurt generatorCode(String passengerPhone) {
        //调用验证码服务 获取验证码
        ResponseResurt<NumberCodeResponse> numberCodeResponse = verificationcodeClient.numberCode(6);
        int code = numberCodeResponse.getData().getNumberCode();

        //存入  redis
        //key value 过期时间
        String key = verificationCodePrefix + passengerPhone;
        redisTemplate.opsForValue().set(key,code+"", 2, TimeUnit.MINUTES);

        System.err.println("number code " + code);

        // 通过短信服务商发送短信

        //返回值
        return ResponseResurt.success(code);
    }
}
