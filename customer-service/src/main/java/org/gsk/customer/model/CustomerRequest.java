package org.gsk.customer.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CustomerRequest(
        @NotNull(message = "Name should not be empty")
        String name,
        String email,
        @NotNull(message = "address should not be empty")
        String address
) {
}
