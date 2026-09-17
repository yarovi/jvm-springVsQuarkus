package org.yasmani.io.springbootapp.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yasmani.io.springbootapp.application.in.ProductUseCase;
import org.yasmani.io.springbootapp.infrastructure.adapter.in.web.dto.ProductRequest;
import org.yasmani.io.springbootapp.infrastructure.adapter.in.web.dto.ProductResponse;
import org.yasmani.io.springbootapp.infrastructure.adapter.in.web.mapper.ProductWebMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductUseCase productUseCase;
  private final ProductWebMapper mapper;

  public ProductController(ProductUseCase productUseCase, ProductWebMapper mapper) {
    this.productUseCase = productUseCase;
    this.mapper = mapper;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<ProductResponse> create(@RequestBody ProductRequest request) {
    return productUseCase.create(mapper.toDomain(request))
        .map(mapper::toResponse);
  }

  @GetMapping("/{id}")
  public Mono<ProductResponse> getById(@PathVariable Long id) {
    return productUseCase.findById(id)
        .map(mapper::toResponse);
  }

  @GetMapping
  public Flux<ProductResponse> getAll() {
    return productUseCase.findAll()
        .map(mapper::toResponse);
  }

  @PutMapping("/{id}")
  public Mono<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
    return productUseCase.update(id, mapper.toDomain(request))
        .map(mapper::toResponse);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> delete(@PathVariable Long id) {
    return productUseCase.deleteById(id);
  }
}
