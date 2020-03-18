package com.kiger.springcloud.controller;

import com.kiger.springcloud.entities.CommonResult;
import com.kiger.springcloud.entities.Payment;
import com.kiger.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zk_kiger
 * @date 2020/3/18
 */

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // openfeign-ribbon，客户端一般默认请求超时等待1秒
        return paymentFeignService.getPaymentFeignTimeout();
    }
}
