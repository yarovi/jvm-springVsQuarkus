package org.yasmani.io.springbootapp.infrastructure.adapter.out.persistencia.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.yasmani.io.springbootapp.infrastructure.adapter.out.entity.ProductEntity;

public interface SpringProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {
}
