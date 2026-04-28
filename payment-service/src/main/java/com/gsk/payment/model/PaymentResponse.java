package com.gsk.payment.model;

import lombok.Builder;

@Builder
public record PaymentResponse(Integer id,
                              Double amount,
                              PaymentMethod paymentMethod,
                              Integer orderId,
                              String orderReference) {
}
