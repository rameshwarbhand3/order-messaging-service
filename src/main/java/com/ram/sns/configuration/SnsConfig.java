package com.ram.sns.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "aws.sns")
@Configuration
@Getter
@Setter
public class SnsConfig {
    private String topicArn;
    private String region;
    private  String endpoint;
}
