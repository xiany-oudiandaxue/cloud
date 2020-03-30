package com.kiger.springcloud.dao;

import com.kiger.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zk_kiger
 * @date 2020/3/30
 */

@Mapper
public interface OrderDao {

    /**
     * 1.新建订单
     */
    void create(Order order);

    /**
     * 2.修改订单状态, 0->1
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
