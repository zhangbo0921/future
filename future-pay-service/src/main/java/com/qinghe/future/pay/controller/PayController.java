package com.qinghe.future.pay.controller;

import com.qinghe.future.commons.result.Result;
import com.qinghe.future.commons.result.ResultType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {

    @PostMapping("/orderPay")
    public Result pay(String orderId,Float price){
        System.out.println(orderId+":"+price);
        return new Result(ResultType.SUCCESS);
    }
}
