package org.acme.api;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.ProductService;
import org.acme.domain.Product;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.List;
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

  private static final Logger LOG =
      Logger.getLogger(ProductResource.class);

  @Inject
  ProductService service;

  @GET
  public Uni<List<Product>> findAll() {
    LOG.infof(
        "GET /products | thread=%s",
        Thread.currentThread().getName()
    );

    return service.findAll();
  }

  // ========================================
  // GET BY ID
  // ========================================

  @GET
  @Path("/{id}")
  public Uni<Response> findById(@PathParam("id") Long id) {

    return service.findById(id)
        .onItem().transform(product -> {

          if (product == null) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .build();
          }

          return Response
              .ok(product)
              .build();
        });
  }


  // ========================================
  // CREATE
  // ========================================

  @POST
  public Uni<Response> create(Product product) {

    return service.create(product)
        .onItem().transform(created ->

            Response
                .created(
                    URI.create(
                        "/products/" + created.id
                    )
                )
                .entity(created)
                .build()
        );
  }


  // ========================================
  // UPDATE
  // ========================================

  @PUT
  @Path("/{id}")
  public Uni<Response> update(
      @PathParam("id") Long id,
      Product product) {

    return service.update(id, product)
        .onItem().transform(updated -> {

          if (updated == null) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .build();
          }

          return Response
              .ok(updated)
              .build();
        });
  }


  // ========================================
  // DELETE
  // ========================================

  @DELETE
  @Path("/{id}")
  public Uni<Response> delete(
      @PathParam("id") Long id) {

    return service.delete(id)
        .onItem().transform(deleted -> {

          if (!deleted) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .build();
          }

          return Response
              .noContent()
              .build();
        });
  }
}
