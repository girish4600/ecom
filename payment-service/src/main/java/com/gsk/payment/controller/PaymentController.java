package com.gsk.payment.controller;

import com.gsk.payment.model.PaymentRequest;
import com.gsk.payment.model.PaymentResponse;
import com.gsk.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentServiceImpl;

    @PostMapping
    public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest paymentRequest){
        return ResponseEntity.ok(paymentServiceImpl.createPayment(paymentRequest));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> createPayment(@PathVariable(name = "paymentId") Integer paymentId){
        return new ResponseEntity<>(paymentServiceImpl.findById(paymentId), HttpStatus.FOUND);
//        return new ResponseEntity<>(customerServiceImpl.findAll(), HttpStatus.FOUND);
    }
}
