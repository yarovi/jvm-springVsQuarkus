package org.yasmani.io.springbootapp.infrastructure.adapter.out.entity;

import jakarta.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("products")
public record ProductEntity(
    @Id
    Long id,
    String name,
    String description,
    BigDecimal price,
    Integer stock
) {}
