package com.kiger.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zk_kiger
 * @date 2020/3/30
 */

@Configuration
@MapperScan({"com.kiger.springcloud.dao"})
public class MybatisConfig {
}
