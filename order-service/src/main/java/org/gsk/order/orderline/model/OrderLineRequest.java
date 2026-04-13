package org.gsk.order.orderline.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.gsk.order.entity.Order;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        @NotNull(message = "Product is mandatory") Integer productId,
        @Positive(message = "quantity should be positive")
        Integer quantity,
        Order order
) {
}
