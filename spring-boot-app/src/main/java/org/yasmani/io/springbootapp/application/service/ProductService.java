package org.yasmani.io.springbootapp.application.service;

import org.springframework.stereotype.Service;
import org.yasmani.io.springbootapp.application.in.ProductUseCase;
import org.yasmani.io.springbootapp.application.out.ProductRepository;
import org.yasmani.io.springbootapp.domain.exception.ProductNotFoundException;
import org.yasmani.io.springbootapp.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService implements ProductUseCase {
  //Create method for crud
  private final ProductRepository repositoryPort;

  public ProductService(ProductRepository repositoryPort) {
    this.repositoryPort = repositoryPort;
  }

  @Override
  public Mono<Product> create(Product product) {
    return repositoryPort.save(product);
  }

  @Override
  public Mono<Product> findById(Long id) {
    return repositoryPort.findById(id)
        .switchIfEmpty(Mono.error(new ProductNotFoundException(id)));
  }

  @Override
  public Flux<Product> findAll() {
    return repositoryPort.findAll();
  }

  @Override
  public Mono<Product> update(Long id, Product product) {
    return repositoryPort.findById(id)
        .switchIfEmpty(Mono.error(new ProductNotFoundException(id)))
        .flatMap(existing -> repositoryPort.save(product.withId(id)));
  }

  @Override
  public Mono<Void> deleteById(Long id) {
    return repositoryPort.existsById(id)
        .flatMap(exists -> {
          if (!exists) {
            return Mono.error(new ProductNotFoundException(id));
          }
          return repositoryPort.deleteById(id);
        });
  }
}
