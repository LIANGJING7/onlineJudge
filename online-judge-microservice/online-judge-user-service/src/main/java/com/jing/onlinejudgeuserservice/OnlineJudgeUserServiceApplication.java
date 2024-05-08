package com.jing.onlinejudgeuserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@MapperScan("com.jing.onlinejudgeuserservice.mapper")
@EnableScheduling
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.jing")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.jing.onlinejudgeserviceclient.service"})
public class OnlineJudgeUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineJudgeUserServiceApplication.class, args);
    }

}
