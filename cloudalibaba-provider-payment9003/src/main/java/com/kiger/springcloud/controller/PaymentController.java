package com.kiger.springcloud.controller;

import com.kiger.springcloud.entities.CommonResult;
import com.kiger.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author zk_kiger
 * @date 2020/3/29
 */

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    private static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L, new Payment(1L, "德鲁大叔"));
        hashMap.put(2L, new Payment(2L, "黑曼巴"));
        hashMap.put(3L, new Payment(3L, "大猩猩"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,
                "from mysql,server port: " + serverPort, payment);
        return result;
    }

}
