package com.ram.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ram.service.dto.Order;
import com.ram.service.dto.OrderPayLoad;
import com.ram.service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private SqsService sqsService;
    @Autowired
    private OrderRepository orderRepository;

    public void process() {
        List<OrderPayLoad> payLoads;
        log.info("Message list is empty, Don't fetch any message from quque ");
        try {
            payLoads = sqsService.fetchMessages();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (OrderPayLoad payLoad : payLoads) {
            payLoad.getOrder().setOrderStatus(Order.OrderStatus.COMPLETE);
            orderRepository.save(payLoad.getOrder());
            log.info("order: [{}]" +
                    "" +
                    "" +
                    " saved successfully", payLoad.getOrder());
            //sqsService.deleteMessages(payLoad);
            log.info("Order payLoad deleted successfully");
        }

    }
}
