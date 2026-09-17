package org.acme.heavy;

import io.smallrye.common.annotation.NonBlocking;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.api.ProductResource;
import org.jboss.logging.Logger;


@Path("/heavy")
public class HeavyTaskResource {

  private static final Logger LOG =
      Logger.getLogger(ProductResource.class);

  @GET
  @Path("/event-loop/{n}")
  @NonBlocking
  public long fibonacciEventLoop(@PathParam("n") int n) {
    LOG.infof(
        """
        [FIBONACCI-START]
        n=%d
        thread=%s
        """,
        n,
        Thread.currentThread().getName()
    );

    long start = System.nanoTime();
    long duration =
        (System.nanoTime() - start) / 1_000_000;

    LOG.infof(
        """
        [FIBONACCI-END]
        n=%d
        duration=%dms
        thread=%s
        """,
        n,
        duration,
        Thread.currentThread().getName()
    );
    return fibonacciRecursive(n);
  }

  private long fibonacciRecursive(int n) {

    if (n <= 1) {
      return n;
    }

    return fibonacciRecursive(n - 1)
        + fibonacciRecursive(n - 2);
  }
}
