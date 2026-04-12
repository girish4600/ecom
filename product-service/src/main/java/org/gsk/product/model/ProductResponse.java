package org.gsk.product.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        Integer productId,
        String productName,
        Integer quantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
