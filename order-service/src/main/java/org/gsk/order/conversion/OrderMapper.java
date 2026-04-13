package org.gsk.order.conversion;

import org.gsk.order.entity.Order;
import org.gsk.order.model.OrderRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMapper {
    public Order dtoToOrderEntity(OrderRequest orderRequest) {
        return Order.builder()
                .customerId(orderRequest.customerId())
                .orderAmount(orderRequest.amount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();
    }
}
