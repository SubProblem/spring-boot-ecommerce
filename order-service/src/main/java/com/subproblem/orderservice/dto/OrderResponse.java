package com.subproblem.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        Integer id,
        Integer quantity,
        BigDecimal price,
        LocalDate orderDate,
        List<Product> products
) {
}
