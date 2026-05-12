package com.gsk.notification.kafka.payment;

import com.gsk.notification.model.PaymentMethod;
import lombok.Builder;

@Builder
public record PaymentConfirmation(
        String orderReference,
        Double amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
