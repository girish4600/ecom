package org.gsk.order.orderline.service;

import org.gsk.order.orderline.model.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderEvent(OrderEvent event) {
        System.out.println("sendOrderEvent :: "+event);
        kafkaTemplate.send("order-confirmed", event.getOrderId(),event);
    }
}
