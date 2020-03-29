package com.kiger.springcloud.service;

import com.kiger.springcloud.entities.CommonResult;
import com.kiger.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author zk_kiger
 * @date 2020/3/29
 */

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级 ---PaymentFallbackService");
    }
}
