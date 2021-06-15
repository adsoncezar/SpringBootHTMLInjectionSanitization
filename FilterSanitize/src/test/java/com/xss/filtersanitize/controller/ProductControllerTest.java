package com.xss.filtersanitize.controller;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Random;
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

  private static final String WAREHOUSE = "downton";
  private static final String CATEGORY = "Mobile";
  private static final String BRAND = "Samsung";
  private static final String NAME = "Galaxy Note 20 Ultra";
  private static final String DESCRIPTION = "5G N9860 12GB/256GB Dual Sim - Mystic Black";
  private static final String[] XSS_EXAMPLE = {
      "<b>%s</b><script>alert('This is not safe!!!');</script>",
      "<h2><IMG SRC=`javascript:alert(\"Message\", 'XSS')`>%s</h2>",
      "<a onmouseover=alert(document.cookie)>%s</a>"};

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

    var request = getProductRequest(NAME, DESCRIPTION);
    ResultActions resultActions = performPostRequest(WAREHOUSE, CATEGORY, BRAND, request);
    assertResult(resultActions);
  }

  @Test
  void shouldCreateProductWithSuccessWhenHeaderContainsXSS() throws Exception {
    String warehouseWithXSS = includeXSS(WAREHOUSE);

    var request = getProductRequest(NAME, DESCRIPTION);
    ResultActions resultActions = performPostRequest(warehouseWithXSS, CATEGORY, BRAND, request);
    assertResult(resultActions);
  }

  @Test
  void shouldCreateProductWithSuccessWhenParameterContainsXSS() throws Exception {
    String bandWithXSS = includeXSS(BRAND);

    var request = getProductRequest(NAME, DESCRIPTION);
    ResultActions resultActions = performPostRequest(WAREHOUSE, CATEGORY, bandWithXSS, request);
    assertResult(resultActions);
  }

  @Test
  void shouldCreateProductWithSuccessWhenBodyContainsXSS() throws Exception {
    String nameWithXSS = includeXSS(NAME);
    String descriptionWithXSS = includeXSS(DESCRIPTION);

    var request = getProductRequest(nameWithXSS, descriptionWithXSS);
    ResultActions resultActions = performPostRequest(WAREHOUSE, CATEGORY, BRAND, request);
    assertResult(resultActions);
  }

  private ResultActions assertResult(ResultActions resultActions) throws Exception {
    return resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.warehouse", is(WAREHOUSE)))
        .andExpect(jsonPath("$.category", is(CATEGORY)))
        .andExpect(jsonPath("$.name", is(NAME)))
        .andExpect(jsonPath("$.description", is(DESCRIPTION)))
        .andExpect(jsonPath("$.brand", is(BRAND)));
  }

  private ProductRequest getProductRequest(String name, String description) {
    return ProductRequest.builder()
        .name(name)
        .description(description)
        .price(BigDecimal.ONE)
        .build();
  }

  private ResultActions performPostRequest(String warehouse, String category, String brand,
      ProductRequest request) throws Exception {
    return mockMvc.perform(
        post("/api/product/" + category)
            .header("warehouse", warehouse)
            .param("brand", brand)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andDo(resultHandler ->
            log.info("Response " + resultHandler.getResponse().getContentAsString())
        );
  }

  private String includeXSS(String field) {
    int index = new Random().nextInt(XSS_EXAMPLE.length);
    return String.format(XSS_EXAMPLE[index], field);
  }

}