package com.xss.AnnotationSanitize.controller;

import com.xss.AnnotationSanitize.entity.Brand;
import com.xss.AnnotationSanitize.entity.Product;
import com.xss.AnnotationSanitize.validation.annotation.NoHtml;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

  @NoHtml
  String name;
  @NoHtml
  String description;
  BrandRequest brand;
  BigDecimal value;

  public Product toModel() {
    return Product.builder().description(this.description).name(this.name).value(this.value)
        .brand(this.brand.toModel()).build();
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  static class BrandRequest {

    String name;

    Brand toModel() {
      return Brand.builder().name(this.name).build();
    }
  }
}
