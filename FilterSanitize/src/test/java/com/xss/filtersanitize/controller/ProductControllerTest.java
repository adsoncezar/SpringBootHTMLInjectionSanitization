package com.xss.filtersanitize.controller;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ProductController.class)
@Slf4j
class ProductControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;


  @BeforeEach
  void before() {
    Assertions.assertNotNull(mockMvc);
  }


  @Test
  void shouldCreateProductWithSuccessWhenThereIsNotHTML() throws Exception {

    String warehouse = "downton";
    String category = "Mobile";
    String brand = "Samsung";
    String name = "Galaxy Note 20 Ultra";
    String description = "5G N9860 12GB/256GB Dual Sim - Mystic Black";

    var request = ProductRequest.builder()
        .name(name)
        .description(description)
        .price(BigDecimal.ONE)
        .build();

    ResultActions resultActions = mockMvc.perform(
        post("/api/product/" + category)
            .header("warehouse", warehouse)
            .param("brand", brand)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andDo(resultHandler ->
            log.info("Response " + resultHandler.getResponse().getContentAsString())
        );

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.warehouse", is(warehouse)))
        .andExpect(jsonPath("$.category", is(category)))
        .andExpect(jsonPath("$.name", is(name)))
        .andExpect(jsonPath("$.description", is(description)))
        .andExpect(jsonPath("$.brand", is(brand)))
    ;
  }

}