package com.gsk.payment.service;

import com.gsk.payment.model.PaymentRequest;
import com.gsk.payment.model.PaymentResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

public interface PaymentService {
    @Nullable Integer createPayment(@Valid PaymentRequest paymentRequest);

    PaymentResponse findById(Integer paymentId);
}
