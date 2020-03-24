package com.kiger.springcloud.controller;

import com.kiger.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zk_kiger
 * @date 2020/3/24
 */

@RestController
public class SendMeeageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessagem() {
        return messageProvider.send();
    }

}
