package com.ram.sns.controller;

import com.ram.sns.dto.OrderRequest;
import com.ram.sns.dto.OrderResponse;
import com.ram.sns.service.OrderService;
import com.ram.sns.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<OrderResponse> publish(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}
