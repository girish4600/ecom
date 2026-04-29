package com.gsk.payment.model;

public record PaymentRequest(
        Integer id,
        Double amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer

) {
}
