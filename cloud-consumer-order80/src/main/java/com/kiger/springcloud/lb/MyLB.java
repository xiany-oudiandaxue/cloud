package com.kiger.springcloud.lb;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义负载均衡类
 * @author zk_kiger
 * @date 2020/3/17
 */

@Component
public class MyLB implements LoadBalance {

    private final AtomicInteger state = new AtomicInteger(0);

    private int getAndIncream() {
        int current;
        int next;
        for (;;) {
            current = state.get();
            next = current >= 2147483647 ? 0 : current + 1;
            System.out.println("第"+next+"次请求");
            if (state.compareAndSet(current, next))
                return next;
        }
    }

    /**
     * 负载均衡算法：
     *  RESt接口请求次数 % 服务器集群总数 = 实际访问服务器位置下表 ，每次服务重启RESt接口从1开始
     */
    @Override
    public ServiceInstance choose(List<ServiceInstance> services) {
        return services.get(getAndIncream() % services.size());
    }
}
