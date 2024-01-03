package com.subproblem.orderservice.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {

    private final KafkaTemplate<String, ProductMessage> kafkaProductTemplate;
    private final KafkaTemplate<String, NotificationMessage> kafkaNotificationTemplate;

    public void sendProductMessage(ProductMessage message) {
        kafkaProductTemplate.send("productTopic", message);
        log.info("Message is sent");
    }

    public void sendNotificationMessage(NotificationMessage message) {
        kafkaNotificationTemplate.send("notificationTopic", message);
    }
}
