package com.kiger.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zk_kiger
 * @date 2020/3/12
 */

@SpringBootApplication
@EnableEurekaServer // 表明是 Eureka 服务注册中心
public class EurekeMain7002 {

    public static void main(String[] args){
        SpringApplication.run(EurekeMain7002.class, args);
    }
}
