package com.gsk.notification.kafka.order;

import lombok.Builder;

@Builder
public record Customer(
        Integer customerId,
        String firstName,
        String lastName,
        String email
) {
}
