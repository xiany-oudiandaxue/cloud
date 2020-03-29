package com.kiger.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kiger.springcloud.entities.CommonResult;
import com.kiger.springcloud.entities.Payment;
import com.kiger.springcloud.myhandler.customerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zk_kiger
 * @date 2020/3/28
 */

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试OK",
                new Payment(2020L, "serial1001"));
    }
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(404, exception.getClass().getCanonicalName()
                + "\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按url限流测试ok",
                new Payment(2020L, "serial1002"));
    }

    /**
     * @SentinelResource
     *  value:资源名
     *  blockHandlerClass:指定配置异常处理方法所在类
     *  blockHandler:配置异常处理方法（不会处理程序异常）
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = customerBlockHandler.class, blockHandler = "handlerException2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "按客户自定义",
                new Payment(2020L, "serial1003"));
    }
}
