package com.kiger.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用Ribbon核心组件 IRule 来自定义负载均衡方式
 *      这个组件需要与 @ComponentScan 这个注解在不同的包下
 * @author zk_kiger
 * @date 2020/3/17
 */

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
