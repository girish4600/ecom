package com.gsk.order.kafka.model;

import lombok.Builder;
import com.gsk.order.model.PaymentMethod;

@Builder
public record OrderNotificationRequest(
        Integer id,
        String reference,
        PaymentMethod paymentMethod,
        Long amount,
        Integer customerId
) {
}
