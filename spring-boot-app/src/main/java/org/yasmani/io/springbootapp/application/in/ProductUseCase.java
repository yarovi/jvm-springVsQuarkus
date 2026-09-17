package org.yasmani.io.springbootapp.application.in;

import org.yasmani.io.springbootapp.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductUseCase {
  Mono<Product> create(Product product);
  Mono<Product> findById(Long id);
  Flux<Product> findAll();
  Mono<Product> update(Long id, Product product);
  Mono<Void> deleteById(Long id);
}
