package com.kiger.springcloud.controller;

import com.kiger.springcloud.service.PaymentHystrix;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zk_kiger
 * @date 2020/3/19
 */

@RestController
@Slf4j
@RequestMapping("/consumer/payment")
// 服务降级(@HystrixCommand)的方法没有指定的fallback方法，就使用默认的fallback方法
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrix paymentHystrix;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrix.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrix.paymentInfo_TimeOut(id);
        return result;
    }
    // 上面方法指定的fallback方法
    private String paymentInfo_TimeOutHandler(Integer id) {
        return "消费者80，对方支付系统繁忙，或自己运行出错请检查自己，请10秒后再试。";
    }


    // --------全局fallback方法----------
    private String payment_Global_FallbackMethod() {
        return "Global异常处理信息,请稍后再试。";
    }
}
