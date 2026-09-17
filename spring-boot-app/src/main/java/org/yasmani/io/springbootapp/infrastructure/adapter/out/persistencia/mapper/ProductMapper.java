package org.yasmani.io.springbootapp.infrastructure.adapter.out.persistencia.mapper;

import org.springframework.stereotype.Component;
import org.yasmani.io.springbootapp.domain.model.Product;
import org.yasmani.io.springbootapp.infrastructure.adapter.out.entity.ProductEntity;

@Component
public class ProductMapper {
  public Product toDomain(ProductEntity entity) {
    return new Product(
        entity.id(),
        entity.name(),
        entity.description(),
        entity.price(),
        entity.stock()
    );
  }
  public ProductEntity toEntity(Product domain) {
    return new ProductEntity(
        domain.id(),
        domain.name(),
        domain.description(),
        domain.price(),
        domain.stock()
    );
  }
}
