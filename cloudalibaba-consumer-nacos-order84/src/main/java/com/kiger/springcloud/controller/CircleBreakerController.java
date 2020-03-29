package com.kiger.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kiger.springcloud.entities.CommonResult;
import com.kiger.springcloud.entities.Payment;
import com.kiger.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zk_kiger
 * @date 2020/3/29
 */

@RestController
@Slf4j
public class CircleBreakerController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String ServerURL;

    // =============== Ribbon ===================
    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")   // 没有配置
//    @SentinelResource(value = "fallback", fallback = "handlerFallback") // fallback只负责业务异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")  // blockHandler只负责sentinel控制台配置违规
    // exceptionsToIgnore:假设报该异常，不在有fallback兜底方法,没有降级效果,异常会打到前台界面
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
                exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult result = restTemplate.getForObject(ServerURL + "/paymentSQL/" + id,
                CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException 非法异常输入");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException 该ID没有对应记录，空指针异常");
        }

        return result;
    }
    // fallback 方法
    public CommonResult<Payment> handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "兜底异常handlerFallback,Exception 内容：" + e.getMessage(), payment);
    }
    // blockHandler 方法
    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException exception) {
        Payment payment = new Payment(id, null);
        return new CommonResult<>(445, "blockHandler-sentinel限流，配置异常：" + exception.getMessage(), payment);
    }


    // =============== OpenFeign ===================
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }

}
