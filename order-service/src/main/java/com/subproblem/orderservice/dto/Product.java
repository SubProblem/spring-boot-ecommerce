package com.subproblem.orderservice.dto;

import java.math.BigDecimal;

public record Product(
        Integer id,
        String description,
        String name,
        BigDecimal price,
        Integer quantity
) {
}
