package org.yasmani.io.springbootapp.infrastructure.adapter.in.web.mapper;

import org.springframework.stereotype.Component;
import org.yasmani.io.springbootapp.domain.model.Product;
import org.yasmani.io.springbootapp.infrastructure.adapter.in.web.dto.ProductRequest;
import org.yasmani.io.springbootapp.infrastructure.adapter.in.web.dto.ProductResponse;

@Component
public class ProductWebMapper {
  public Product toDomain(ProductRequest request) {
    return new Product(null, request.name(), request.description(), request.price(), request.stock());
  }

  public ProductResponse toResponse(Product domain) {
    return new ProductResponse(domain.id(), domain.name(), domain.description(), domain.price(), domain.stock());
  }
}
