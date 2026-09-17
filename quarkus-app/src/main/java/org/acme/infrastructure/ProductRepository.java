package org.acme.infrastructure;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.Product;

@ApplicationScoped
public class ProductRepository
    implements PanacheRepository<Product> {
}
