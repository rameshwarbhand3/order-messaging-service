package com.ram.sns.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ram.sns.configuration.SnsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;


@Service
@Slf4j
public class TopicService {
    private SnsClient snsClient;
    private SnsConfig snsConfig;
    private ObjectMapper objectMapper;

    @Autowired
    public TopicService(SnsClient snsClient, SnsConfig snsConfig, ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.snsConfig = snsConfig;
        this.objectMapper = objectMapper;
    }

    public <T> void publishOrder(T inputObject) {
        try {
            String message = objectMapper.writeValueAsString(inputObject);
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(snsConfig.getTopicArn())
                    .build();
            PublishResponse result = snsClient.publish(request);
            log.info("{} Message sent Status is {}", result.messageId(), result.sdkHttpResponse());
        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
