package com.ram.sns.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

import java.net.URI;

@Configuration
public class SnsBeanConfig {
    private SnsConfig snsConfig;
    @Bean
    public SnsClient snsClient(SnsConfig snsConfig){
        return SnsClient.builder()
                .endpointOverride(URI.create(snsConfig.getEndpoint()))
                .region(Region.of(snsConfig.getRegion()))
                .build();
    }



}
