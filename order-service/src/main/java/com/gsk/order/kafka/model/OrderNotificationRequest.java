package com.gsk.order.kafka.model;

import com.gsk.order.model.customer.CustomerResponse;
import com.gsk.order.model.product.ProductPurchaseResponse;
import lombok.Builder;
import com.gsk.order.model.PaymentMethod;

import java.util.List;

@Builder
public record OrderNotificationRequest(
        Integer id,
        String reference,
        PaymentMethod paymentMethod,
        Long amount,
        CustomerResponse customer,
        List<ProductPurchaseResponse> products
) {
}
