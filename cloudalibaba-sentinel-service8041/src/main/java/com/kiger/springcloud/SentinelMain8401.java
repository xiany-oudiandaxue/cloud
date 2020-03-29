package com.kiger.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zk_kiger
 * @date 2020/3/27
 */

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMain8401 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelMain8401.class, args);
    }
}
