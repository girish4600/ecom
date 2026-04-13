package org.gsk.order.model.product;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponse(

        Integer id,
        String productName,
        Integer quantity,
        BigDecimal price
) {
}
