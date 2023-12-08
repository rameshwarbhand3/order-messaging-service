package com.ram.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SnsMessage {
    @JsonProperty("MessageId")
    private String messageId;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("TopicArn")
    private String topicArn;

    @JsonProperty("Message")
    private String message;

}
