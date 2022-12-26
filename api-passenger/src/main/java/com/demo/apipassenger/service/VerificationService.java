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

    public ResponseResurt generatorCode(String passengerPhone) {
        //调用验证码服务 获取验证码
        ResponseResurt<NumberCodeResponse> numberCodeResponse = verificationcodeClient.numberCode(5);
        int code = numberCodeResponse.getData().getNumberCode();

        //存入  redis
        System.err.println("number code " + code);

        //返回值
        return ResponseResurt.success(code);
    }
}
