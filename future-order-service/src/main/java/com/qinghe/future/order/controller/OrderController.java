package com.qinghe.future.order.controller;

import com.qinghe.future.commons.result.Result;
import com.qinghe.future.commons.result.ResultType;
import com.qinghe.future.order.entity.Orderinfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('order_list')")
    public Result list(){
        List<Orderinfo> orderList = new ArrayList<>();
        Orderinfo orderinfo = new Orderinfo();
        orderinfo.setOrderId("1");
        orderinfo.setPrice("99.95");
        orderList.add(orderinfo);
        return new Result(ResultType.SUCCESS,orderList);
    }
}
