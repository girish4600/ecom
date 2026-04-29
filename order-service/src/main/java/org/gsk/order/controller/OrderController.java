package org.gsk.order.controller;

import jakarta.validation.Valid;
import org.gsk.order.model.OrderRequest;
import org.gsk.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderServiceImpl;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody @Valid OrderRequest orderRequest) {
        var response = orderServiceImpl.placeOrder(orderRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<String> getCustomer(@PathVariable(name = "customerId") Integer customerId) {
        orderServiceImpl.getCustomer(customerId);
        return ResponseEntity.ok("orderServiceImpl.placeOrder(orderRequest)");
    }
}
