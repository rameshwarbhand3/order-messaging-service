package com.ram.service.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "aws.sqs")
@Configuration
@Getter
@Setter
public class SqsConfig {
    private String region;
    private String endpoint;
    private Integer pollingDuration;
    private Integer messageCount;
    private String queueUrl;
}
