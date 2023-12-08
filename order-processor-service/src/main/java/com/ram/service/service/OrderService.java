package com.ram.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ram.service.dto.OrderPayLoad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.List;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private SqsService sqsService;

    public void process() {
        List<OrderPayLoad> payLoads = null;
        if(payLoads == null){
            log.info("Message list is empty, Don't fetch any message from quque ");
        }
        try {
            payLoads = sqsService.fetchMessages();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (OrderPayLoad payLoad : payLoads) {
            log.info("order:{}", payLoad.getOrder().toString());
            sqsService.deleteMessages(payLoad);
            log.info("Order payLoad deleted successfully");
        }

    }
}
