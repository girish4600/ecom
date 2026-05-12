package com.gsk.notification.kafka.order;

import com.gsk.notification.model.PaymentMethod;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderConfirmation(
        String reference,
        PaymentMethod paymentMethod,
        Long amount,
        Customer customer,
        List<Product> products
) {
}
