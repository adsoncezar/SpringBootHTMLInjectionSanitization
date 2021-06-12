package com.xss.annotationsanitize.controller;

import com.xss.annotationsanitize.sanitization.NoHtml;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NoHtml
    String name;
    @NoHtml
    String description;
    BigDecimal value;

}
