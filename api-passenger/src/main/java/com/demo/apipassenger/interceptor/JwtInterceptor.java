package com.demo.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.demo.intarnalcommon.dto.ResponseResurt;
import com.demo.intarnalcommon.dto.TokenResult;
import com.demo.intarnalcommon.util.JwtUtils;
import com.demo.intarnalcommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by jzx on 2022/12/29 14:02
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultStr = "token invalid";

        String token = request.getHeader("Authorization");

        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            // token签名错误
            resultStr = "token sign error";
            result = false;
        } catch (TokenExpiredException e) {
            // token过期超时
            resultStr = "token time out";
            result = false;
        } catch (AlgorithmMismatchException e) {
            // token算法错误
            resultStr = "token algo error";
            result = false;
        } catch (Exception e) {
            resultStr = "token invalid";
            result = false;
        }

        if (!result || tokenResult == null) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResurt.fail(resultStr)).toString());
            result = false;
        } else {
            String tokenKey = RedisPrefixUtils.generatorTokenKey(tokenResult.getPhone(), tokenResult.getIdentity());
            String tokenRedis = redisTemplate.opsForValue().get(tokenKey);

            if (StringUtils.isBlank(tokenRedis) || !token.trim().equals(tokenRedis.trim())) {
                PrintWriter out = response.getWriter();
                out.print(JSONObject.fromObject(ResponseResurt.fail(resultStr)).toString());
                result = false;
            } else {
                result = true;
            }
        }

//        return HandlerInterceptor.super.preHandle(request, response, handler);
        return result;
    }
}
