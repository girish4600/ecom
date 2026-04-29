package org.gsk.order.model.customer;

import lombok.Builder;

@Builder
public record CustomerResponse(
        Integer customerId,
        String firstName,
        String lastName,
        String email
) {
}
