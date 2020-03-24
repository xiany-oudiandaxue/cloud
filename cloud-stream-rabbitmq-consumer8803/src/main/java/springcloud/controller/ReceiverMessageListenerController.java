package springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author zk_kiger
 * @date 2020/3/24
 */

@Component
@EnableBinding(Sink.class) // 1.定义消息接收管道
public class ReceiverMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    // 2.通过接受管道获取消息 message
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("consumer01,----->" + message.getPayload()+"\t port: " + serverPort);
    }

}
