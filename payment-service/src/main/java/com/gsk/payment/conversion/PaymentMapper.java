package com.gsk.payment.conversion;

import com.gsk.payment.entity.Payment;
import com.gsk.payment.model.PaymentRequest;
import com.gsk.payment.model.PaymentResponse;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentMapper {
    public Payment dtoToEntity(PaymentRequest paymentRequest) {
        return Payment.builder()
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .orderId(paymentRequest.orderId())
                .build();
    }

    public PaymentResponse entityToDto(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderReference("")
                .amount(payment.getAmount())
                .orderId(payment.getOrderId())
                .paymentMethod(payment.getPaymentMethod())
                .build();
    }
}
