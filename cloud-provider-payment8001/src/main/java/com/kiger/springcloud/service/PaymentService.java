package com.kiger.springcloud.service;

import com.kiger.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author zk_kiger
 * @date 2020/3/11
 */

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
