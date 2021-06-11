package com.xss.AnnotationSanitize.controller;

import com.xss.AnnotationSanitize.service.ProductService;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping
  public ResponseEntity<?> getProducts() {
    return ResponseEntity.ok(productService.getProducts());
  }

  @GetMapping("/{idProduct}")
  public ResponseEntity<?> getProduct(@PathParam("idProduct") String idProduct) {
    return ResponseEntity.ok(productService.getProduct(Long.parseLong(idProduct)));
  }

  @PostMapping
  public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest productRequest,
      @RequestHeader String idDeposity, @RequestHeader String nameDeposity) {
    productService.save(productRequest, idDeposity, nameDeposity);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
