package com.demo.apipassenger.interceptor;

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

        TokenResult tokenResult = JwtUtils.checkToken(token);

        if (!result || tokenResult == null) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResurt.fail(resultStr)).toString());
            result = false;
        } else {
            String tokenKey = RedisPrefixUtils.generatorTokenKey(tokenResult.getPhone(), tokenResult.getIdentity(), tokenResult.getTokenType());
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
