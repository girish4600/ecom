package org.gsk.order.controller;

import jakarta.validation.Valid;
import org.gsk.order.model.OrderRequest;
import org.gsk.order.orderline.model.OrderEvent;
import org.gsk.order.orderline.service.OrderProducer;
import org.gsk.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderServiceImpl;

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody @Valid OrderRequest orderRequest) {
        var response = orderServiceImpl.placeOrder(orderRequest);
//        OrderEvent event = new OrderEvent(response, "gsk@gmail.com", "CONFIRMED");
//        orderProducer.sendOrderEvent(event);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<String> getCustomer(@PathVariable(name = "customerId") Integer customerId) {
        orderServiceImpl.getCustomer(customerId);
        return ResponseEntity.ok("orderServiceImpl.placeOrder(orderRequest)");
    }
}
