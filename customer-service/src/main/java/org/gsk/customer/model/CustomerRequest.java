package org.gsk.customer.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CustomerRequest(
        @NotNull(message = "First Name should not be empty")
        String firstName,
        @NotNull(message = "Last Name should not be empty")
        String lastName,
        @NotNull(message = "address should not be empty")
        String address,
        String email
) {
}
