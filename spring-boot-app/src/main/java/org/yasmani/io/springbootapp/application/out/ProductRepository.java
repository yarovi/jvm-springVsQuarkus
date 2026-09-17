package org.yasmani.io.springbootapp.application.out;

import org.yasmani.io.springbootapp.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  Mono<Product> save(Product product);

  Mono<Product> findById(Long id);

  Flux<Product> findAll();

  Mono<Void> deleteById(Long id);

  Mono<Boolean> existsById(Long id);
}
