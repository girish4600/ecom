package com.gsk.order.model.payment;

import lombok.Builder;
import com.gsk.order.model.PaymentMethod;
import com.gsk.order.model.customer.CustomerResponse;

@Builder
public record PaymentRequest(
        Long amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer

) {
}
