package org.gsk.order.model.payment;

import lombok.Builder;
import org.gsk.order.model.PaymentMethod;
import org.gsk.order.model.customer.CustomerResponse;

@Builder
public record PaymentRequest(
        Long amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer

) {
}
