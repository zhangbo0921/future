package com.qinghe.future.order.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Orderinfo implements Serializable {

    private String orderId;
    private String price;
}
