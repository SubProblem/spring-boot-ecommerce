package com.subproblem.orderservice.service;

import com.subproblem.orderservice.dto.OrderRequest;
import com.subproblem.orderservice.dto.OrderResponse;
import com.subproblem.orderservice.dto.Product;
import com.subproblem.orderservice.entity.Order;
import com.subproblem.orderservice.producer.NotificationMessage;
import com.subproblem.orderservice.producer.Producer;
import com.subproblem.orderservice.producer.ProductMessage;
import com.subproblem.orderservice.repository.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Service
public class OrdersService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;
    private final Producer producer;


    public ResponseEntity<?> makeOrder(OrderRequest request, HttpServletRequest http) {

        var order = Order.builder()
                .userId(Integer.valueOf(http.getHeader("auth-header-id")))
                .productId(request.productId())
                .dateOfOrder(LocalDate.now())
                .quantity(request.quantity())
                .totalPrice(request.price())
                .build();

        orderRepository.save(order);

        // Send product id and quantity to order-service and update total amount of product by Id

        var productMessage = new ProductMessage(order.getQuantity(), order.getProductId());
        producer.sendProductMessage(productMessage);

        // Send customers email and purchased product to notification-service

        var notificationMessage = NotificationMessage.builder()
                .email(http.getHeader("user-email"))
                .dateOfOrder(order.getDateOfOrder())
                .build();

        producer.sendNotificationMessage(notificationMessage);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public ResponseEntity<?> getOrders(HttpServletRequest request) {

        var userId = Integer.valueOf(request.getHeader("auth-header-id"));

        var orders = orderRepository.findAllByUserId(userId);

        List<Product> products = webClient.build()
                .get()
                .uri("lb://PRODUCTS-SERVICE/api/v1/products")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList().block();


        var orderDto = orders.stream()
                .map(order -> {
                    List<Product> productsForOrder = products.stream()
                            .filter(product -> order.getProductId().equals(product.id()))
                            .map(product -> new Product(
                                    product.id(),
                                    product.description(),
                                    product.name(),
                                    product.price(),
                                    product.quantity()
                                    ))
                            .toList();
                    return new OrderResponse(
                            order.getId(),
                            order.getQuantity(),
                            order.getTotalPrice(),
                            order.getDateOfOrder(),
                            productsForOrder
                            );
                })
                .toList();

        return ResponseEntity.ok(orderDto);
    }
}
