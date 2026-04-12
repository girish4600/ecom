package org.gsk.customer.handler;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int status,
        String message,
        long timestamp
) {
}
