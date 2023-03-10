package com.demo.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 把拦截器注册到容器里
 * Created by jzx on 2022/12/29 14:21
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                //拦截路径
                .addPathPatterns("/**")
                //不拦截路径
                .excludePathPatterns("/verification-code") //获取验证码
                .excludePathPatterns("/verification-code-check") //校验验证码
        ;
    }
}
