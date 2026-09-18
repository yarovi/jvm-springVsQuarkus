package org.acme.application;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.api.ProductResource;
import org.acme.domain.Product;
import org.acme.infrastructure.ProductRepository;
import org.jboss.logging.Logger;

import java.util.List;
@ApplicationScoped
public class ProductService {

  private static final Logger LOG =
      Logger.getLogger(ProductResource.class);

  @Inject
  ProductRepository repository;

  @WithSession
  public Uni<List<Product>> findAll() {
    LOG.infof(
        "[SERVICE] findAll thread=%s",
        Thread.currentThread().getName());
    return repository.listAll();
  }



  @WithSession
  public Uni<Product> findById(Long id) {
    return repository.findById(id);
  }

  @WithSession
  @WithTransaction
  public Uni<Product> create(Product product) {
    product.id = null;

    return repository.persist(product)
        .replaceWith(product);
  }

  @WithSession
  @WithTransaction
  public Uni<Product> update(
      Long id,
      Product request) {

    return repository.findById(id)
        .onItem().ifNotNull()
        .transform(product -> {

          product.name = request.name;
          product.price = request.price;
          product.description = request.description;
          product.stock = request.stock;

          return product;
        });
  }

  @WithTransaction
  public Uni<Boolean> delete(Long id) {
    return repository.deleteById(id);
  }
}
