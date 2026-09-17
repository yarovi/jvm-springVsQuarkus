package org.yasmani.io.springbootapp.infrastructure.adapter.in.web.dto;

import java.math.BigDecimal;

public record ProductResponse(
    Long id,
    String name,
    String description,
    BigDecimal price,
    Integer stock
) {}
