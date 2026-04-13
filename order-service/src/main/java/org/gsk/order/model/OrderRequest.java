package org.gsk.order.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.gsk.order.model.product.ProductPurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderRequest(
        Integer id,
        String reference,
        @NotNull(message = "Payment method should precised")
        PaymentMethod paymentMethod,
        @Positive(message = "Order amount should be positive")
        Long amount,
        @NotNull(message = "customerId should be present")
        Integer customerId,

        @NotEmpty(message = "There should be at least one purchase Request")
        List<ProductPurchaseRequest> purchaseRequest

) {
}
