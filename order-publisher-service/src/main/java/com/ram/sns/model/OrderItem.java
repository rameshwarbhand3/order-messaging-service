package com.ram.sns.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderItem {
    private Long id;
    private String name;
    private int quantity;
    private int price;
    private String description;
}
