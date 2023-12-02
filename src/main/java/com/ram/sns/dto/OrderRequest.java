package com.ram.sns.dto;

import com.ram.sns.model.OrderItem;
import com.ram.sns.model.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrderRequest {
    private String customerId;
    private List<OrderItem> orderItems;
}
