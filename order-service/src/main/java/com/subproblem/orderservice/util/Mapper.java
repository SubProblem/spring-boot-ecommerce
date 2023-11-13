package com.subproblem.orderservice.util;

import com.subproblem.orderservice.dto.OrderResponse;
import com.subproblem.orderservice.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class Mapper {

    public OrderResponse orderToDto(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getDateOfOrder(),
                null
        );
    }

    public List<OrderResponse> mapToList(List<Order> orders) {
        return orders.stream()
                .map(this::orderToDto)
                .toList();
    }
}
