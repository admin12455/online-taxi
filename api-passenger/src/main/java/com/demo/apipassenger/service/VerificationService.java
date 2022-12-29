package com.demo.apipassenger.service;

import com.demo.apipassenger.remote.ServicePassengerUserClient;
import com.demo.apipassenger.remote.ServiceVerificationcodeClient;
import com.demo.intarnalcommon.constant.CommonStatusEnum;
import com.demo.intarnalcommon.constant.IdentityConstant;
import com.demo.intarnalcommon.dto.ResponseResurt;
import com.demo.intarnalcommon.request.VerificationCodeDto;
import com.demo.intarnalcommon.response.NumberCodeResponse;
import com.demo.intarnalcommon.response.TokenResponse;
import com.demo.intarnalcommon.util.JwtUtils;
import com.demo.intarnalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by jzx on 2022/12/16 15:51
 */
@Service
public class VerificationService {

    @Autowired
    private ServiceVerificationcodeClient verificationcodeClient;

    @Autowired
    private ServicePassengerUserClient passengerUserClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public ResponseResurt generatorCode(String passengerPhone) {
        //调用验证码服务 获取验证码
        ResponseResurt<NumberCodeResponse> numberCodeResponse = verificationcodeClient.numberCode(6);
        int code = numberCodeResponse.getData().getNumberCode();

        //存入  redis
        //key value 过期时间
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        redisTemplate.opsForValue().set(key,code+"", 2, TimeUnit.MINUTES);

        System.err.println("number code " + code);

        // 通过短信服务商发送短信

        //返回值
        return ResponseResurt.success(code);
    }

    /**
     * 校验验证码
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResurt CheckVerificationCode(String passengerPhone, String verificationCode) {
        //根据手机号去redis获取验证码
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        String code = redisTemplate.opsForValue().get(key);

        //比较验证码
        if (StringUtils.isBlank(code)||!verificationCode.trim().equals(code.trim())) {
            return ResponseResurt.fail(CommonStatusEnum.VERFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERFICATION_CODE_ERROR.getValue());
        }
        //判断原来是否有用户
        VerificationCodeDto verificationCodeDto = new VerificationCodeDto();
        verificationCodeDto.setPassengerPhone(passengerPhone);
        passengerUserClient.loginOrRegister(verificationCodeDto);

        //颁发令牌
        String token = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);

        //将token 保存到redis中
        redisTemplate.opsForValue().set(RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY), token, 30, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResurt.success(tokenResponse);
    }
}
