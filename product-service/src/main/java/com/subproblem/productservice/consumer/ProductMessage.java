package com.subproblem.productservice.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductMessage {

    private Integer quantity;
    private Integer productId;

}
