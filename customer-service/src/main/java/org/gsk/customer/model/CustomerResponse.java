package org.gsk.customer.model;

import lombok.Builder;

@Builder
public record CustomerResponse(
        Integer customerId,
        String firstName,
        String lastName,
        String email,
        String address
) {
}
