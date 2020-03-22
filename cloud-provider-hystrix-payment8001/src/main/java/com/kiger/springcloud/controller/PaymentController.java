package com.kiger.springcloud.controller;

import com.kiger.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zk_kiger
 * @date 2020/3/19
 */

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String SERVER_PORT;

    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        final String result = paymentService.paymentInfo_OK(id);
        log.info("***result:" + result);
        return result;
    }

    /**
     * 非正常访问
     *
     * @param id
     * @return
     */
    @GetMapping("/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        final String result = paymentService.paymentInfo_TimeOut(id);
        log.info("***result:" + result);
        return result;
    }


    /**
     * 服务熔断
     * http://localhost:8001/payment/circuit/1
     *
     * @param id
     * @return
     */
    @GetMapping("/circuit/{id}")
    @HystrixCommand
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("***result:" + result);
        return result;
    }
}
