package com.demo.intarnalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.intarnalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzx on 2022/12/29 10:47
 */
public class JwtUtils {

    //盐
    private static final String SIGN = "asdQWE#!POVB&*zTFCf%";

    //手机号
    private static final String JWT_KEY_PHONE = "phone";
    //身份
    private static final String JWT_KEY_IDENTITY = "identity";

    private static final String JWT_TOKEN_TYPE = "tokenType";
    private static final String JWT_TOKEN_TIME = "tokenTime";

    //生成
    public static String generatorToken(String phone, String identity, String tokenType) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_TOKEN_TYPE, tokenType);
        //token里添加时间 防止每次生成的token一样
        map.put(JWT_TOKEN_TIME, Calendar.getInstance().getTime().toString());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date time = calendar.getTime();

        // 生成 build
        JWTCreator.Builder builder = JWT.create();
        // 整合 map
        map.forEach((k,v) -> builder.withClaim(k,v));
        // 整合过期时间
//        builder.withExpiresAt(time);
        // 生成
        String token = builder.sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    //解析
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        TokenResult tokenResult = new TokenResult();
        Claim phone = verify.getClaim(JWT_KEY_PHONE);
        tokenResult.setPhone(phone.asString());
        Claim identity = verify.getClaim(JWT_KEY_IDENTITY);
        tokenResult.setIdentity(identity.asString());
        Claim tokenType = verify.getClaim(JWT_TOKEN_TYPE);
        tokenResult.setTokenType(tokenType.asString());
        return tokenResult;
    }

    /**
     * 校验token解析是否出错
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token) {
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e) {
            tokenResult = null;
        }
        return tokenResult;
    }

}
