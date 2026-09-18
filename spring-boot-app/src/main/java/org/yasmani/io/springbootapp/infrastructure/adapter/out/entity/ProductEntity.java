package org.yasmani.io.springbootapp.infrastructure.adapter.out.entity;

import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
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
) implements Persistable<Long> {
    @Override
    public @Nullable Long getId() {
        return id;
    }

    @Transient
    @Override
    public boolean isNew() {
        return id == null;
    }
}
