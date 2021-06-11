package com.xss.FilterSanitize.service;

import com.xss.FilterSanitize.entity.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xss.FilterSanitize.controller.ProductRequest;
import com.xss.FilterSanitize.entity.Deposity;
import com.xss.FilterSanitize.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  public Product getProduct(Long idProduct) {
    return productRepository.getById(idProduct);
  }

  public void save(ProductRequest productRequest, String idDeposity, String nameDeposity) {
    var deposity = Deposity.builder().id(Long.getLong(idDeposity)).name(nameDeposity).build();
    var product = productRequest.toModel();
    product.setDeposity(deposity);

    productRepository.save(product);
  }
}
