package com.ram.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Getter
@Setter
@Document("orderItems")
public class Order {
    private String orderId;
    private String customerId;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private int totalPrice;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderItems=" + orderItems +
                ", orderStatus=" + orderStatus +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Builder
    @Getter
    @Setter
    public static class OrderItem {
        private Long id;
        private String name;
        private int quantity;
        private int price;
        private String description;

        @Override
        public String toString() {
            return "OrderItem{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    public enum OrderStatus {
        CREATED,
        CANCELLED,
        PENDING,
        COMPLETE;

    }
}
