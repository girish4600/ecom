package com.gsk.order.conversion;

import com.gsk.order.entity.Order;
import com.gsk.order.model.OrderRequest;
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
