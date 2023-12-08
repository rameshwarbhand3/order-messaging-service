package com.ram.sns.service;

import com.ram.sns.dto.OrderRequest;
import com.ram.sns.dto.OrderResponse;
import com.ram.sns.model.Order;
import com.ram.sns.model.OrderItem;
import com.ram.sns.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private TopicService topicService;

    public OrderService(TopicService topicService) {
        this.topicService = topicService;
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = buildOrder(orderRequest);
        topicService.publishOrder(order);
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .build();
    }

    private Order buildOrder(OrderRequest orderRequest) {
        String orderId = UUID.randomUUID().toString();
        int totalPrice = calculateTotalPrice(orderRequest.getOrderItems());
        return Order.builder()
                .orderId(orderId)
                .customerId(orderRequest.getCustomerId())
                .orderStatus(OrderStatus.CREATED)
                .orderItems(orderRequest.getOrderItems())
                .totalPrice(totalPrice)
                .build();
    }

    private int calculateTotalPrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .reduce(0, (totPrice, eachPrice) -> totPrice + eachPrice);
//        int totalPrice = 0;
//        for (OrderItem orderItem: orderItems) {
//            totalPrice = totalPrice + (orderItem.getQuantity() * orderItem.getPrice());
//        }
    }
}
