package com.gsk.payment.kafka.model;

import com.gsk.payment.model.PaymentMethod;
import lombok.Builder;

@Builder
public record PaymentNotificationRequest(
        String orderReference,
        Double amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
