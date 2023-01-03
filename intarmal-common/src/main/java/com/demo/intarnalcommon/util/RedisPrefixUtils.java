package com.demo.intarnalcommon.util;

/**
 * Created by jzx on 2022/12/29 15:33
 */
public class RedisPrefixUtils {

    //验证码前缀
    private static final String verificationCodePrefix = "passage-verification-code-";
    //token前缀
    private static final String TokenPrefix = "token-";

    /**
     * 根据手机号生成Key
     * @param passengerPhone 手机号
     * @return
     */
    public static String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号和身份标识获取 token key
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone, String identity, String tokenType) {
        return TokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
