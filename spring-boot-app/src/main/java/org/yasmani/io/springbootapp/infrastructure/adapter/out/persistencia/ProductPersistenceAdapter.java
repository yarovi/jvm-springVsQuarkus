package org.yasmani.io.springbootapp.infrastructure.adapter.out.persistencia;

import org.springframework.stereotype.Component;
import org.yasmani.io.springbootapp.application.out.ProductRepository;
import org.yasmani.io.springbootapp.domain.model.Product;
import org.yasmani.io.springbootapp.infrastructure.adapter.out.persistencia.mapper.ProductMapper;
import org.yasmani.io.springbootapp.infrastructure.adapter.out.persistencia.repository.SpringProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductPersistenceAdapter implements ProductRepository {

  final private SpringProductRepository repository;
  final private ProductMapper mapper;

  public ProductPersistenceAdapter(SpringProductRepository springProductRepository, ProductMapper productMapper) {
    this.repository = springProductRepository;
    this.mapper = productMapper;
  }

  @Override
  public Mono<Product> save(Product product) {
    return repository.save(mapper.toEntity(product))
        .map(mapper::toDomain);
  }

  @Override
  public Mono<Product> findById(Long id) {
    return repository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public Flux<Product> findAll() {
    return repository.findAll()
        .map(mapper::toDomain);
  }

  @Override
  public Mono<Void> deleteById(Long id) {
    return repository.deleteById(id);
  }

  @Override
  public Mono<Boolean> existsById(Long id) {
    return repository.existsById(id);
  }
}
