package org.gsk.order.outer_api;

import org.gsk.order.model.payment.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient( name = "payment-service"/*, url = "${application.config.payment-url:http://localhost:9094}"*/)
public interface PaymentService {

    @PostMapping("/v1/payment")
    public ResponseEntity<Integer> createPayment(@RequestBody PaymentRequest paymentRequest);
}
