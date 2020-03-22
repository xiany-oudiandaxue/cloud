package com.kiger.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 为服务接口中的方法作统一的fallback处理
 * @author zk_kiger
 * @date 2020/3/19
 */

@Component
public class PaymentFallbackService implements PaymentHystrix {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fall back--paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----PaymentFallbackService fall back--paymentInfo_TimeOut";
    }
}
