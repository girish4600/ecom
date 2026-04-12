package org.gsk.product.handler;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int status,
        String message,
        long timestamp
) {
}
