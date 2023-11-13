package com.subproblem.productservice.dto;

import java.math.BigDecimal;

public record ProductRequest(
         String description,
         String name,
         BigDecimal price,
         Integer quantity
) {
}
