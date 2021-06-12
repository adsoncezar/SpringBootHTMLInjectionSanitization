package com.xss.annotationsanitize.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

    @PostMapping("/{category}")
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody @Valid ProductRequest productRequest,
            @RequestParam String category,
            @RequestHeader String warehouseName) {
        return ResponseEntity.ok(ProductResponse.builder()
                .product(productRequest)
                .category(category)
                .warehouseName(warehouseName)
                .build());
    }

}
