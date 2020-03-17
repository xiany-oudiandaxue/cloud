package com.kiger.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author zk_kiger
 * @date 2020/3/17
 */
public interface LoadBalance {

    ServiceInstance choose(List<ServiceInstance> services);
}
