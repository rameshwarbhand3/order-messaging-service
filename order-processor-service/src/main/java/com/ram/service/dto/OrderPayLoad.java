package com.ram.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderPayLoad {
    private Order order;
    private String receiptHandle;
}
