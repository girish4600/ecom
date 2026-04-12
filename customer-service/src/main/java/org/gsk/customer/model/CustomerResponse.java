package org.gsk.customer.model;

import lombok.Builder;

@Builder
public record CustomerResponse(
        Integer customerId,
        String name,
        String email,
        String address
) {
}
