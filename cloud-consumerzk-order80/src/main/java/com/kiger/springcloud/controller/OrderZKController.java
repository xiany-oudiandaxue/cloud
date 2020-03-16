package com.kiger.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zk_kiger
 * @date 2020/3/16
 */

@RestController
@Slf4j
public class OrderZKController {

    public static final String INVOKE_RUL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_RUL + "/payment/zk", String.class);
        return result;
    }

}
