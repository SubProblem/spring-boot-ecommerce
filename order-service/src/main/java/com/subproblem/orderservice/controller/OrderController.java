package com.subproblem.orderservice.controller;

import com.subproblem.orderservice.dto.OrderRequest;
import com.subproblem.orderservice.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<?> getOrders(HttpServletRequest request) {
        return ordersService.getOrders(request);
    }

    @PostMapping
    public ResponseEntity<?> makeOrders(@RequestBody OrderRequest order, HttpServletRequest request) {
        return ordersService.makeOrder(order, request);
    }

}
