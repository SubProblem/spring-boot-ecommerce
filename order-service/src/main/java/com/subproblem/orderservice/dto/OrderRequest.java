package com.subproblem.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderRequest(
        Integer productId,
        Integer quantity,
        BigDecimal price
) {
}
