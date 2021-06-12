package com.xss.annotationsanitize.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String warehouseName;
    private String category;
    private ProductRequest product;
}
