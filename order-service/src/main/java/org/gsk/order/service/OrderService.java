package org.gsk.order.service;

import jakarta.validation.Valid;
import org.gsk.order.model.OrderRequest;

public interface OrderService {
    String placeOrder(@Valid OrderRequest orderRequest);

    String getCustomer(Integer customerId);
}
