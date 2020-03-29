package com.kiger.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zk_kiger
 * @date 2020/3/27
 */

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "------TestA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "-------TestB";
    }

    @GetMapping("/testD")
    public String testD() {
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        log.info("测试RT");

        log.info("测试 异常比例");
        int age = 10/0;

        return "--- TestD";
    }

    @GetMapping("/testE")
    public String testE() {
        log.info("test4 测试异常数");
        int age = 10/0;
        return "--- TestE 测试异常数";
    }

    /**
     * @SentinelResource
     *  value:唯一标示
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1
            ,@RequestParam(value = "p2",required = false) String p2) {
        return "--- TestHotKey";
    }
    public String dealHotKey(String p1, String p2, BlockException exception) {
        return "--- dealHotKey Exception";
    }
}
