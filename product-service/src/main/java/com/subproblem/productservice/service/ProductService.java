package com.subproblem.productservice.service;

import com.subproblem.productservice.dto.ProductRequest;
import com.subproblem.productservice.entity.Product;
import com.subproblem.productservice.repository.ProductRepository;
import com.subproblem.productservice.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Mapper mapper;

    public ResponseEntity<?> addProduct(ProductRequest request) {

        var product = Product.builder()
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .quantity(request.quantity())
                .build();

        productRepository.save(product);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteProduct(Integer id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        productRepository.delete(product);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    public ResponseEntity<?> getAllProducts() {

        var products = mapper.convertToList(productRepository.findAll());
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<?> getProductByUserId(Integer id) {
        return null;
    }
}
