package com.gsk.order.orderline.service;

import com.gsk.order.orderline.model.OrderLineRequest;

public interface OrderLineService {
    Integer saveOrderLine(OrderLineRequest orderLineRequest);
}
