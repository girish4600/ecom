package org.gsk.product.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest (
        String productName,
        Integer quantity,
        BigDecimal price,
        Integer categoryId) {
}
