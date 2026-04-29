package com.gsk.notification.kafka.order;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Product(
        Integer productId,
        String productName,
        Integer quantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
