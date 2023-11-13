package com.subproblem.productservice.controller;


import com.subproblem.productservice.dto.ProductRequest;
import com.subproblem.productservice.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductService productService;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "hello user number: " + request.getHeader("auth-header-id");
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getProductByUserId(@PathVariable Integer id) {
        return productService.getProductByUserId(id);
    }
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(Integer id) {
        return productService.deleteProduct(id);
    }
}
