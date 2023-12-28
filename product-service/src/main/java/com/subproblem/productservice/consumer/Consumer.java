package com.subproblem.productservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subproblem.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Service
public class Consumer {

    private final ProductRepository productRepository;

    @KafkaListener(topics = "productTopic", groupId = "myGroup")
    public void consume(ProductMessage message) {
        log.info("Product message: {}", message);


        var product = productRepository.findProductById(message.getProductId())
                .orElseThrow(() -> new NoSuchElementException("product not found"));

        var updatedQuantity = product.getQuantity() - message.getQuantity();

        product.setQuantity(updatedQuantity);

        productRepository.save(product);

        log.info("updated product: {}", product.getQuantity());
    }
}
