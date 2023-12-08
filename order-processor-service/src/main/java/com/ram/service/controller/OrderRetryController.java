package com.ram.service.controller;

import com.ram.service.dto.OrderPayLoad;
import com.ram.service.service.OrderService;
import com.ram.service.service.SqsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sqs.model.Message;

@RestController
@RequestMapping("/api/")
@Slf4j
public class OrderRetryController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private SqsService sqsService;

    @PostMapping("orders/retry")
    public ResponseEntity<?> retry() {
        orderService.process();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
