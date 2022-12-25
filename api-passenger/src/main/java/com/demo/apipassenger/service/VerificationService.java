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

    public String generatorCode(String passengerPhone) {
        //调用验证码服务 获取验证码

        ResponseResurt<NumberCodeResponse> numberCodeResponse = verificationcodeClient.numberCode(5);
        int code = numberCodeResponse.getCode();
        //存入  redis
        System.out.println("code:" + code );
        System.err.println("number code " + numberCodeResponse.getData().getNumberCode());

        //返回值
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");
        return result.toString();
    }
}
