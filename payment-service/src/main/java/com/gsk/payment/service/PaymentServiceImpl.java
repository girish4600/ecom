package com.gsk.payment.service;

import com.gsk.payment.conversion.PaymentMapper;
import com.gsk.payment.model.PaymentRequest;
import com.gsk.payment.model.PaymentResponse;
import com.gsk.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper mapper;

    @Override
    public @Nullable Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(mapper.dtoToEntity(paymentRequest));
        return payment.getId();
    }

    @Override
    public PaymentResponse findById(Integer paymentId) {
        log.info("checking payment with ID :: {}",paymentId);
        return paymentRepository.findById(paymentId).map(mapper::entityToDto).orElseThrow(() -> new RuntimeException("payment details not found"));
    }
}
