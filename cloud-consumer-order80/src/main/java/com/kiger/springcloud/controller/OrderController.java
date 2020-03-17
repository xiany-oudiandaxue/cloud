package com.kiger.springcloud.controller;

import com.kiger.springcloud.entities.CommonResult;
import com.kiger.springcloud.entities.Payment;
import com.kiger.springcloud.lb.MyLB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author zk_kiger
 * @date 2020/3/11
 */

@RestController
@Slf4j
public class OrderController {

    //    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MyLB lb;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",
                payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,
                CommonResult.class);
    }

    /**
     * 1.使用 getForObject 方法返回获取响应信息的json格式
     * 2.使用 getForEntity 方法可以获得响应头、响应体、响应状态码等信息
     */
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult getPaymentEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entities = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id,
                CommonResult.class);

        if (entities.getStatusCode().is2xxSuccessful()) {
            return entities.getBody();
        } else {
            return new CommonResult(404, "failed");
        }
    }

    /**
     * 测试自定义的负载均衡器
     */
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = lb.choose(instances);
        URI uri = instance.getUri();
        String result = restTemplate.getForObject(uri + "/payment/lb", String.class);
        return result;
    }
}
