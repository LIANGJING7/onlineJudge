package com.jing.onlinejudgeserviceclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.jing")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.jing.onlinejudgeserviceclient.service"})
public class OnlineJudgeServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineJudgeServiceClientApplication.class, args);
    }

}
