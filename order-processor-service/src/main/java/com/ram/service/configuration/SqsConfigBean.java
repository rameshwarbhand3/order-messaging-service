package com.ram.service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Configuration
public class SqsConfigBean {

    @Autowired
    private SqsConfig sqsConfig;

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.of(sqsConfig.getRegion()))
                .endpointOverride(URI.create(sqsConfig.getEndpoint()))
                .build();
    }
}
