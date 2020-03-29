package com.kiger.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kiger.springcloud.entities.CommonResult;
import com.kiger.springcloud.entities.Payment;

/**
 * @author zk_kiger
 * @date 2020/3/28
 */

public class customerBlockHandler {

    public static CommonResult handlerException1(BlockException exception) {
        return new CommonResult(4444, "按客户自定义, global handlerException ---- 1");
    }


    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "按客户自定义, global handlerException ---- 2");
    }
}
