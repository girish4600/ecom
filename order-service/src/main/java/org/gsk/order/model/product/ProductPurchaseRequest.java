package org.gsk.order.model.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductPurchaseRequest(

        @NotNull(message = "Product is mandatory")
        Integer productId,
        @NotNull(message = "quantity is mandatory")
        @Positive(message = "quantity should be positive")
        Integer quantity

) {
}
