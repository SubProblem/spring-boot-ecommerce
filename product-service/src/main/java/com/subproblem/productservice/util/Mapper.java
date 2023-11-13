package com.subproblem.productservice.util;

import com.subproblem.productservice.dto.ProductResponse;
import com.subproblem.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class Mapper {

    public ProductResponse productToDto(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getDescription(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public List<ProductResponse> convertToList(List<Product> products) {

        return products.stream()
                .map(this::productToDto)
                .toList();
    }
}
