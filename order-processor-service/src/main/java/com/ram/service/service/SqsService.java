package com.ram.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ram.service.configuration.SqsConfig;
import com.ram.service.dto.Order;
import com.ram.service.dto.OrderPayLoad;
import com.ram.service.dto.SnsMessage;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class SqsService {
    private SqsClient sqsClient;
    private SqsConfig sqsConfig;
    private ObjectMapper objectMapper;

    public SqsService(SqsClient sqsClient, SqsConfig sqsConfig, ObjectMapper objectMapper) {
        this.sqsClient = sqsClient;
        this.sqsConfig = sqsConfig;
        this.objectMapper = objectMapper;
    }

    public List<OrderPayLoad> fetchMessages() throws JsonProcessingException {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(sqsConfig.getQueueUrl())
                .waitTimeSeconds(sqsConfig.getPollingDuration())
                .maxNumberOfMessages(sqsConfig.getNumberOfMessagesToFetch())
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest)
                .messages();
        List<OrderPayLoad> payLoads = new ArrayList<>();
        for (Message message : messages) {
            SnsMessage snsMessage = objectMapper.readValue(message.body(), SnsMessage.class);
            Order order = objectMapper.readValue(snsMessage.getMessage(), Order.class);
            OrderPayLoad payLoad = OrderPayLoad.builder()
                    .order(order).
                    receiptHandle(message.receiptHandle())
                    .build();
            payLoads.add(payLoad);
        }
        return payLoads;
    }

    public void deleteMessages(OrderPayLoad orderPayLoad) {

        DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder().queueUrl(sqsConfig.getQueueUrl())
                .receiptHandle(orderPayLoad.getReceiptHandle())
                .build();
        sqsClient.deleteMessage(deleteMessageRequest);

    }

}
