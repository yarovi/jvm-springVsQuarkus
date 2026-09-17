package org.yasmani.io.springbootapp.domain.model;


import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record Product(
    Long id,
    String name,
    String description,
    BigDecimal price,
    Integer stock
) {
  public Product withId(Long newId) {
    return new Product(newId, this.name, this.description, this.price, this.stock);
  }
}
