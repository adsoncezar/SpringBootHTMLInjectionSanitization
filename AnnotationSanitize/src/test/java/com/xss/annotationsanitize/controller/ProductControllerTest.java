package com.xss.annotationsanitize.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
@Slf4j
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

}