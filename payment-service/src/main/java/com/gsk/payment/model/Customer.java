package com.gsk.payment.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        Integer customerId,
        @NotNull(message = "firstName is required")
        String firstName,
        @NotNull(message = "lasName is required")
        String lastName,
        @NotNull(message = "email is required")
        @Email(message = "The customer is not correctly formatted")
        String email
) {
}
