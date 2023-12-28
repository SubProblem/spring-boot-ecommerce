package com.subproblem.orderservice.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {

    private final KafkaTemplate<String, ProductMessage> kafkaTemplate;


    public void sendProductMessage(ProductMessage message) {
        log.info("Hellooooooooooo");
        kafkaTemplate.send("productTopic", message);
        log.info("Message is sent");
    }
}
