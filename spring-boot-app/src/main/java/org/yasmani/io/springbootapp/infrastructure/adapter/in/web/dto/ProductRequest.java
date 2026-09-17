package org.yasmani.io.springbootapp.infrastructure.adapter.in.web.dto;

import java.math.BigDecimal;

public record ProductRequest(
    String name,
    String description,
    BigDecimal price,
    Integer stock
) {}
