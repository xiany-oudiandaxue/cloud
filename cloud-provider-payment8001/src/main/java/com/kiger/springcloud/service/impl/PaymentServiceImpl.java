package com.kiger.springcloud.service.impl;

import com.kiger.springcloud.dao.PaymentDao;
import com.kiger.springcloud.entities.Payment;
import com.kiger.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zk_kiger
 * @date 2020/3/11
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
