package com.demo.verificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by jzx on 2022/12/16 16:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class VerificationCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(VerificationCodeApplication.class, args);
    }
}
