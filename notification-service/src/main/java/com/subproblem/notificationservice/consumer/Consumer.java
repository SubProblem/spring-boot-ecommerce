package com.subproblem.notificationservice.consumer;

import com.subproblem.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    private final NotificationService notificationService;


    @KafkaListener(topics = "notificationTopic", groupId = "orderGroup")
    public void sendEmail(NotificationMessage message) {
        log.info("Order message: {}", message);
        String subject = "Thank You for Your Order";
        String body = "Your order was made on" + message.getDateOfOrder().toString();
        notificationService.sendEmail(message.getEmail(), subject, body);
    }
}
