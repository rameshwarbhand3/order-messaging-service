package com.ram.sns.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Order {
    private String orderId;
    private String customerId;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private int totalPrice;

}
