package org.gsk.order.orderline.service;

import org.gsk.order.orderline.model.OrderLineRequest;

public interface OrderLineService {
    Integer saveOrderLine(OrderLineRequest orderLineRequest);
}
