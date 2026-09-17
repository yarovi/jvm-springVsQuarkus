package org.acme.infrastructure.util;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.UUID;


@Provider
public class RequestTraceFilter
    implements ContainerRequestFilter,
    ContainerResponseFilter {

  private static final Logger LOG =
      Logger.getLogger(RequestTraceFilter.class);

  private static final String START_TIME = "startTime";
  private static final String REQUEST_ID = "requestId";

  @Override
  public void filter(ContainerRequestContext request) {

    String requestId =
        UUID.randomUUID().toString().substring(0, 8);

    request.setProperty(REQUEST_ID, requestId);
    request.setProperty(START_TIME, System.nanoTime());

    LOG.infof(
        "[START] requestId=%s method=%s path=%s thread=%s",
        requestId,
        request.getMethod(),
        request.getUriInfo().getPath(),
        Thread.currentThread().getName()
    );
  }

  @Override
  public void filter(
      ContainerRequestContext request,
      ContainerResponseContext response) {

    long start =
        (long) request.getProperty(START_TIME);

    long duration =
        (System.nanoTime() - start) / 1_000_000;

    LOG.infof(
        "[END] requestId=%s status=%d duration=%dms thread=%s",
        request.getProperty(REQUEST_ID),
        response.getStatus(),
        duration,
        Thread.currentThread().getName()
    );
  }
}
