package com.ram.service.controller;

import com.ram.service.service.OrderService;
import io.awspring.cloud.sns.annotation.endpoint.NotificationMessageMapping;
import io.awspring.cloud.sns.annotation.endpoint.NotificationSubscriptionMapping;
import io.awspring.cloud.sns.annotation.handlers.NotificationMessage;
import io.awspring.cloud.sns.annotation.handlers.NotificationSubject;
import io.awspring.cloud.sns.handlers.NotificationStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/orders/notify", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
@Slf4j
public class OrderProcessorController {
    @Autowired
    private OrderService orderService;

    @NotificationSubscriptionMapping
    public void handleSubscriptionMessage(NotificationStatus status) {
        log.info(" Subscription request received...");
        status.confirmSubscription();
    }

    @NotificationMessageMapping
    public void handleNotificationMessage(@NotificationSubject String subject, @NotificationMessage String message) {
        log.info("Message received, subject: {}, message: {}", subject, message);
        orderService.process();
    }

}
