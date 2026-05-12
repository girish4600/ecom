package com.gsk.order.service;

import jakarta.validation.Valid;
import com.gsk.order.model.OrderRequest;

public interface OrderService {
    String placeOrder(@Valid OrderRequest orderRequest);

    String getCustomer(Integer customerId);
}
