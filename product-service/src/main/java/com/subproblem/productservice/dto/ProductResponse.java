package com.subproblem.productservice.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String description,
        String name,
        BigDecimal price,
        Integer quantity
) {
}
