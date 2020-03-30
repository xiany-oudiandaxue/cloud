package com.kiger.springcloud.service.impl;

import com.kiger.springcloud.dao.OrderDao;
import com.kiger.springcloud.domain.Order;
import com.kiger.springcloud.service.AccountService;
import com.kiger.springcloud.service.OrderService;
import com.kiger.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zk_kiger
 * @date 2020/3/30
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    /**
     * @GlobalTransactional
     *  rollbackFor:发生那些异常需要回滚
     */
    @Override
    @GlobalTransactional(name = "test-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        // 1.新建订单
        log.info("----------->开始新建订单");
        orderDao.create(order);

        // 2.扣减库存
        log.info("----------->订单微服务开始调用库存，库存扣减Count start");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("----------->订单微服务开始调用库存，库存减少Count end");

        // 3.扣减账户余额
        log.info("----------->订单微服务开始调用账户，余额扣减Money start");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("----------->订单微服务开始调用账户，余额扣减Money end");

        // 4.修改订单状态
        log.info("----------->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("----------->修改订单状态结束");

        log.info("----------->订单处理完成");
    }
}
