package com.xss.filtersanitize.controller;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

  private String warehouse;
  private String category;
  private String name;
  private String description;
  private String brand;
  private BigDecimal price;
}
