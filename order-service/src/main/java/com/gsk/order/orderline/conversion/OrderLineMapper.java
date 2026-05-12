package com.gsk.order.orderline.conversion;

import com.gsk.order.entity.OrderLine;
import com.gsk.order.orderline.model.OrderLineRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderLineMapper {
    public OrderLine entityToDTO(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .productId(orderLineRequest.productId())
                .quantity(orderLineRequest.quantity())
                .order(orderLineRequest.order())
                .build();
    }
}
