package com.kiger.springcloud.service.impl;

import com.kiger.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author zk_kiger
 * @date 2020/3/24
 */
@EnableBinding(Source.class) // 1.定义消息推送管道
public class MessageProviderImpl implements IMessageProvider {

    // 2.获取消息推送管道
    @Resource
    private MessageChannel output;

    // 3.通过管道将消息推送到RabbitMQ
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("******serial: " + serial);
        return null;
    }

}
