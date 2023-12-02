package com.ram.sns.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public enum OrderStatus {
    CREATED,
    CANCELLED,
    PENDING,
    COMPLETE;

}
