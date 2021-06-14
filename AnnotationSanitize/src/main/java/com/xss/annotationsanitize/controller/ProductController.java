package com.xss.annotationsanitize.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@Validated
public class ProductController {

  @PostMapping("/{category}")
  public ResponseEntity<ProductResponse> createProduct(
      @RequestBody @Valid ProductRequest productRequest,
      @RequestHeader String warehouse,
      @PathVariable String category,
      @RequestParam String brand
  ) {
    return ResponseEntity.ok(ProductResponse.builder()
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .brand(brand)
        .category(category)
        .warehouse(warehouse)
        .build());
  }

}
